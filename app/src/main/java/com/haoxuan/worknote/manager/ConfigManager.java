package com.haoxuan.worknote.manager;

import java.util.HashMap;

/**
 * Created by skateboard on 2016/1/4.
 */
public class ConfigManager {
    private static HashMap<String,String> configMap=new HashMap<String,String>();

    public static void set(String key,String data)
    {
        configMap.put(key,data);
    }

    public static String get(String key)
    {
        return configMap.get(key);
    }


}
