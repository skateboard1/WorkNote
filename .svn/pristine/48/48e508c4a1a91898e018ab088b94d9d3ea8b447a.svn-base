package com.haoxuan.worknote.application;

import android.app.Application;

import com.haoxuan.worknote.bean.config.Config;
import com.haoxuan.worknote.util.XMLParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by skateboard on 2016/1/4.
 */
public class NoteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(getAssets().open("config.xml")));
            Config config=new Config();
            XMLParser.parser(reader,config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
