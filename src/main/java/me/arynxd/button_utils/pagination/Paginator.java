package me.arynxd.button_utils.pagination;

import java.util.concurrent.TimeUnit;

public interface Paginator {
    // Default time for paginator to timeout due to the interaction token expiring
    int DEFAULT_TIMEOUT = 900;
    TimeUnit DEFAULT_TIMEOUT_UNIT = TimeUnit.SECONDS;
    boolean DEFAULT_DELETE_ON_TIMEOUT = true;

    void paginate();

    int getTimeout();

    TimeUnit getTimeoutUnit();

    int getPage();
}
