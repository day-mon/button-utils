package me.arynxd.button_utils.builder.menu;

import java.util.List;
import java.util.Map;
import me.arynxd.button_utils.builder.Builder;
import me.arynxd.button_utils.menu.Menu;

public abstract class MenuBuilder extends Builder<Menu> {
    private Map<Integer, List<String>> pageMapper;

    public Map<Integer, List<String>> getPageMapper() {
        return pageMapper;
    }

    public void setPageMapper(Map<Integer, List<String>> pageMapper) {
        this.pageMapper = pageMapper;
    }
}
