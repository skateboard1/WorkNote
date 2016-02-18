package com.haoxuan.worknote.bean.menu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by skateboard on 16-2-8.
 */
public class MenuList implements Serializable {
    private ArrayList<MenuDetail> menus;

    public ArrayList<MenuDetail> getMenus() {
        return menus;
    }

    public void setMenu(ArrayList<MenuDetail> menus) {
        this.menus = menus;
    }
}
