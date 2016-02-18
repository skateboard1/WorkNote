package com.haoxuan.worknote.bean.config;

import java.util.HashMap;

/**
 * Created by skateboard on 2016/1/4.
 */
public class Config {

    private static HashMap<String,Page> pages;

    public static HashMap<String, Page> getPages() {
        return pages;
    }

    public static void setPages(HashMap<String, Page> pages) {
        Config.pages = pages;
    }

    public static String getPageName(String id)
    {
        if(pages!=null && !pages.isEmpty()) {
            return pages.get(id).getClassName();
        }
        return null;
    }
}
