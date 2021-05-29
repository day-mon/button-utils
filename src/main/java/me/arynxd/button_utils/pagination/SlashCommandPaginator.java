package me.arynxd.button_utils.pagination;

import java.util.concurrent.TimeUnit;

public class SlashCommandPaginator implements Paginator {
    @Override
    public void paginate() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int getTimeout() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public TimeUnit getTimeoutUnit() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int getPage() {
        throw new UnsupportedOperationException("not implemented");
    }
}
