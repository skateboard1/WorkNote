package com.haoxuan.worknote.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.haoxuan.worknote.constant.K;

import network.SocketTask;
import network.SocketTask.Method;
import network.SocketTask.OnSocketRequestListener;

/**
 * Created by skateboard on 2016/1/4.
 */
public class BaseActivity extends AppCompatActivity implements OnSocketRequestListener {
    protected SocketTask mSocketTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActivityCreateView();
        onActivityCreated();
    }

    protected void initGetSocketTask(String method, String dstHost, int port) {
        mSocketTask = new SocketTask(method, dstHost, port);
        mSocketTask.setOnSocketRequestListener(this);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onSuccess(String result) {

    }

    protected void onActivityCreateView() {
        initGetSocketTask(Method.SGET, K.DEST_HOST, K.DEFAULT_PORT);
    }

    protected void onActivityCreated() {

    }
}
