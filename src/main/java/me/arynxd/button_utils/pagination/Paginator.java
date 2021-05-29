package me.arynxd.button_utils.pagination;

import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.entities.Emoji;

public interface Paginator {
    int DEFAULT_TIMEOUT = 10;
    TimeUnit DEFAULT_TIMEOUT_UNIT = TimeUnit.SECONDS;
    boolean DEFAULT_DELETE_ON_TIMEOUT = true;

    String ARROW_RIGHT_UNICODE = "\u27A1\uFE0F";
    String ARROW_LEFT_UNICODE = "\u2B05\uFE0F";
    String WASTEBASKET_UNICODE = "\uD83D\uDDD1\uFE0F";

    Emoji ARROW_RIGHT_EMOJI = Emoji.ofUnicode(ARROW_RIGHT_UNICODE);
    Emoji ARROW_LEFT_EMOJI = Emoji.ofUnicode(ARROW_LEFT_UNICODE);
    Emoji WASTEBASKET_EMOJI = Emoji.ofUnicode(WASTEBASKET_UNICODE);

    void paginate();

    int getTimeout();

    TimeUnit getTimeoutUnit();

    int getPage();
}
