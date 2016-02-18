package com.haoxuan.worknote.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.WindowManager;

import com.haoxuan.worknote.R;

/**
 * Created by skateboard on 16-2-5.
 */
public class DrawerActivity extends BaseActivity {

    protected DrawerLayout drawerLayout;

    protected NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
    }



    private void initView()
    {
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer);

    }

    protected void changeContent(String fragmentName,Bundle bundle) {
//        try {
//            Class obj = Class.forName(fragmentName);
//            Fragment fragment= (Fragment) obj.newInstance();
//            fragment.setArguments(bundle);
//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.container,bundle).commit();
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

    }


}
