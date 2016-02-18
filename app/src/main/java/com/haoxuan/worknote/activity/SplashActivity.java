package com.haoxuan.worknote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import com.google.gson.Gson;
import com.haoxuan.worknote.R;
import com.haoxuan.worknote.bean.config.Config;
import com.haoxuan.worknote.bean.config.Page;
import com.haoxuan.worknote.bean.menu.MenuList;
import com.haoxuan.worknote.constant.K;
import com.haoxuan.worknote.fragment.dialog.AlertDialogFragment;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

import network.SocketTask;

/**
 * Created by skateboard on 16-2-6.
 */
public class SplashActivity extends BaseActivity {

    private HashMap<String, Page> pages;
    private Handler handler;

    @Override
    protected void onActivityCreateView() {
        super.onActivityCreateView();
        setContentView(R.layout.activity_splash);
        initConfig();

    }

    private void initConfig() {
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setConfig();
                mSocketTask.setTitle(K.GET_MENU);
                mSocketTask.execute();
            }
        },1500);

    }

    private void setConfig() {
        pages = new HashMap<>();
        XmlPullParser parser = getResources().getXml(R.xml.config);
        try {
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                if (type == XmlPullParser.START_TAG) {
                    if ("page".equals(parser.getName())) {
                        String id = null;
                        String className = null;
                        for (int i = 0; i < parser.getAttributeCount(); i++) {
                            String name = parser.getAttributeName(i);
                            if ("id".equals(name)) {
                                id = parser.getAttributeValue(i);

                            } else if ("className".equals(name)) {
                                className = parser.getAttributeValue(i);
                            }
                        }
                        Page page = new Page();
                        page.setClassName(className);
                        page.setId(id);
                        pages.put(id, page);
                    }
                }
                type = parser.next();
            }
            Config.setPages(pages);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openMainActivity(Bundle bundle) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSuccess(String result) {
        super.onSuccess(result);
        Gson gson=new Gson();
        MenuList menuList=gson.fromJson(result,MenuList.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("menu_list",menuList);
        openMainActivity(bundle);
    }

    @Override
    public void onError(String message) {
        super.onError(message);
        AlertDialogFragment alertDialog=new AlertDialogFragment();
        alertDialog.show(getFragmentManager().beginTransaction(),null);
    }
}
