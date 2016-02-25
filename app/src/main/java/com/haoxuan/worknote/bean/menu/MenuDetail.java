package com.haoxuan.worknote.bean.menu;

import java.io.Serializable;

/**
 * Created by skateboard on 16-2-8.
 */
public class MenuDetail implements Serializable {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
