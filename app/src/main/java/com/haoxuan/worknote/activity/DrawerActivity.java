package com.haoxuan.worknote.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.view.WindowManager;

import com.haoxuan.worknote.R;
import com.haoxuan.worknote.fragment.FirstFragment;

/**
 * Created by skateboard on 16-2-5.
 */
public class DrawerActivity extends BaseActivity {
    private ActionBarDrawerToggle toggle;
    protected DrawerLayout drawer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onContentChanged() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onContentChanged();
        initView();
    }

    private void initView()
    {
        initDrawer();
        initToggle();
    }

    private void initToggle()
    {
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("OPEN");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("CLOSED");
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initDrawer()
    {
        drawer = (DrawerLayout) findViewById(R.id.drawer);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        if (toggle != null) {
            toggle.syncState();
        }
    }

}


