package com.haoxuan.worknote.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by skateboard on 2016/1/4.
 */
public class BaseActivity extends AppCompatActivity {
    protected FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager=getSupportFragmentManager();
    }

    public void changeFragment(String fragmentName)
    {

    }
}
