package me.arynxd.button_utils.pagination;

import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.entities.Emoji;

public interface Paginator {
    int DEFAULT_TIMEOUT = 10;
    TimeUnit DEFAULT_TIMEOUT_UNIT = TimeUnit.SECONDS;
    boolean DEFAULT_DELETE_ON_TIMEOUT = true;

    void paginate();

    int getTimeout();

    TimeUnit getTimeoutUnit();

    int getPage();
}
