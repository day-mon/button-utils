package me.arynxd.button_utils.builder.menu;

import javax.annotation.Nonnull;
import me.arynxd.button_utils.menu.Menu;
import me.arynxd.button_utils.menu.StandardMenu;

public class StandardMenuBuilder extends MenuBuilder {
    @Nonnull
    @Override
    protected Menu compile() {
        return new StandardMenu(this);
    }
}
