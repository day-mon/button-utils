package me.arynxd.button_utils.pagination;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import me.arynxd.button_utils.Constants;
import me.arynxd.button_utils.builder.pagination.StandardPaginatorBuilder;
import me.arynxd.button_utils.util.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardButtonPaginator implements Paginator {
    public static final Logger LOGGER = LoggerFactory.getLogger(StandardButtonPaginator.class);

    private final TimeUnit timeoutUnit;
    private final int timeout;

    private final Predicate<ButtonClickEvent> predicate;
    private final List<MessageEmbed> embeds;
    private final boolean deleteOnTimeout;
    private final EventWaiter waiter;
    private final long channelId;
    private final JDA jda;

    private final int maxPage;
    private final ActionRow actionRow;
    private long messageId = -1;
    private int page = 0;

    public StandardButtonPaginator(StandardPaginatorBuilder builder) {
        this.timeoutUnit = builder.getTimeoutUnit();
        this.timeout = builder.getTimeout();

        this.predicate = builder.getPredicate();
        this.embeds = builder.getEmbeds();
        this.deleteOnTimeout = builder.isDeleteOnTimeout();
        this.waiter = builder.getWaiter();
        this.channelId = builder.getChannelId();
        this.jda = builder.getJDA();

        this.maxPage = embeds.size();

        this.actionRow = ActionRow.of(
                Button.primary(makeToken(), Constants.ARROW_LEFT_EMOJI),
                Button.primary(makeToken(), Constants.ARROW_RIGHT_EMOJI),
                Button.danger(makeToken(), Constants.WASTEBASKET_EMOJI)
        );
    }

    @Override
    public void paginate() {
        send();
        doWait();
    }

    private void doWait() {
        waiter.waitForEvent(ButtonClickEvent.class, predicate, this::switchPage, timeout, timeoutUnit, () -> {
            if (this.deleteOnTimeout) {
                if (this.messageId == -1) {
                    LOGGER.error("ID not set (this should never happen)", new IllegalStateException());
                    return;
                }

                MessageChannel channel = getChannel();
                if (channel == null) {
                    LOGGER.error("Channel does not exist for ID " + this.channelId, new IllegalStateException());
                    return;
                }

                channel.deleteMessageById(this.messageId).queue();
            }
        });
    }

    private void switchPage(ButtonClickEvent event) {
        event.deferEdit().queue();
        String emoji = event.getButton().getEmoji().getName();
        Message message = event.getMessage();

        if (!predicate.test(event)) {
            return;
        }

        switch (emoji) {
            case Constants.ARROW_LEFT_UNICODE:
                page--;
                if (page < 0) {
                    page = maxPage - 1;
                }
                break;
            case Constants.ARROW_RIGHT_UNICODE:
                page++;
                if (page >= maxPage) {
                    page = 0;
                }
                break;
            case Constants.WASTEBASKET_UNICODE:
                message.delete().queue();
                return;
        }
        message.editMessage(embeds.get(page))
                .setActionRows(actionRow)
                .queue();
        doWait();
    }

    private void send() {
        MessageChannel channel = getChannel();

        if (channel == null) {
            throw new IllegalArgumentException("Channel does not exist for ID " + channelId);
        }

        channel.sendMessage(embeds.get(page))
                .setActionRows(actionRow)
                .queue(m -> this.messageId = m.getIdLong());
    }

    private MessageChannel getChannel() {
        MessageChannel channel = jda.getTextChannelById(channelId);
        if (channel == null) {
            channel = jda.getPrivateChannelById(channelId);
        }

        return channel;
    }

    private String makeToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public int getTimeout() {
        return timeout;
    }


    @Override
    public TimeUnit getTimeoutUnit() {
        return this.timeoutUnit;
    }


    @Override
    public int getPage() {
        return page;
    }
}
