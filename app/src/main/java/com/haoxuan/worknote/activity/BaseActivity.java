package com.haoxuan.worknote.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.haoxuan.worknote.R;
import com.haoxuan.worknote.constant.K;

import network.SocketTask;
import network.SocketTask.Method;
import network.SocketTask.OnSocketRequestListener;

/**
 * Created by skateboard on 2016/1/4.
 */
public class BaseActivity extends AppCompatActivity implements OnSocketRequestListener {
    protected Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null)
        {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onSuccess(String result) {

    }


    protected SocketTask initSocket()
    {
        SocketTask mSocketTask=new SocketTask();
        mSocketTask.setOnSocketRequestListener(this);
        return mSocketTask;
    }

}
