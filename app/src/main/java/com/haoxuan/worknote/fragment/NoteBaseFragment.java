package com.haoxuan.worknote.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.haoxuan.worknote.fragment.dialog.ProgressDialogFragment;

/**
 * Created by skateboard on 2016/1/4.
 */
public abstract class NoteBaseFragment extends Fragment {

    protected abstract void loadingFinished();
    protected abstract int getLayoutId();
    private ProgressDialogFragment myProgressDialog;
    private RequestQueue queue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        myProgressDialog=new ProgressDialogFragment();
//        myProgressDialog.show(getChildFragmentManager(),"loading");
//        myProgressDialog.setCancelable(false);
//        queue= Volley.newRequestQueue(getActivity());

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        view=inflater.inflate(getLayoutId(),container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingFinished();
//        StringRequest request=new StringRequest(Request.Method.GET, "http://blog.csdn.net/guolin_blog/article/details/17482095", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                loadingFinished();
//                myProgressDialog.dismiss();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                myProgressDialog.dismiss();
//            }
//        });
//        queue.add(request);


    }



}
