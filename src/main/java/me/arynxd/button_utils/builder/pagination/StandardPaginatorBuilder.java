package me.arynxd.button_utils.builder.pagination;

import javax.annotation.Nonnull;
import me.arynxd.button_utils.pagination.Paginator;
import me.arynxd.button_utils.pagination.StandardButtonPaginator;

public class StandardPaginatorBuilder extends PaginatorBuilder {
    @Nonnull
    @Override
    protected Paginator compile() {
        return new StandardButtonPaginator(this);
    }
}
