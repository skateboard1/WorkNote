package com.haoxuan.worknote.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by skateboard on 16-2-9.
 */
public class HeadRecyclerView extends RecyclerView {

    private View mHeader;

    private View mFooter;

    private Adapter mBaseAdapter;

    public HeadRecyclerView(Context context) {
        super(context);
    }

    public HeadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public View addHeadView(int layoutId) {
        return addHeadView(LayoutInflater.from(getContext()).inflate(layoutId, this, false));
    }

    public View addHeadView(View view) {
        mHeader = view;
        if(getAdapter()!=null)
        {
            setAdapter(getAdapter());
        }
        return view;
    }

    public View addFootView(int layoutId) {
        return addFootView(LayoutInflater.from(getContext()).inflate(layoutId, this, false));
    }

    public View addFootView(View view) {
        mFooter = view;
        if(getAdapter()!=null)
        {
            setAdapter(getAdapter());
        }
        return view;
    }

    public void removeHeadView()
    {
        mHeader=null;
        setAdapter(getAdapter());
    }

    public void removeFootView()
    {
        mFooter=null;
        setAdapter(getAdapter());
    }

    @Override
    public void setAdapter(Adapter adapter) {
        adapter = new CustomAdapter(adapter, mHeader, mFooter);
        super.setAdapter(adapter);
    }

    private class CustomAdapter extends Adapter {

        Adapter baseAdapter;

        View headView;

        View footView;

        final int HEAD_TYPE = 101;

        final int NORMAL_TYPE = 102;

        final int FOOT_TYPE = 103;

        CustomAdapter(Adapter adapter, View headView, View footView) {
            this.baseAdapter = adapter;

            this.headView = headView;

            this.footView = footView;
        }

        @Override
        public int getItemViewType(int position) {

            if (headView != null && position == 0) {
                return HEAD_TYPE;
            } else if (footView != null && position == getItemCount()-1) {
                return FOOT_TYPE;
            } else {
                return NORMAL_TYPE;
            }

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == HEAD_TYPE) {
                return new FixedHolder(headView);
            } else if (viewType == FOOT_TYPE) {
                return new FixedHolder(footView);
            } else {
                return baseAdapter.onCreateViewHolder(parent, viewType);
            }

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if(headView!=null && footView!=null)
            {
                if(position>0 && position<getItemCount()-1)
                {
                    baseAdapter.onBindViewHolder(holder, position-1);
                }
            }
            else if (headView!=null) {
                if(position>0) {
                    baseAdapter.onBindViewHolder(holder, position-1);
                }
            }
            else if(footView!=null)
            {
                if(position<getItemCount()-1)
                {
                    baseAdapter.onBindViewHolder(holder,position);
                }
            }
            else
            {
                baseAdapter.onBindViewHolder(holder,position);
            }

        }

        @Override
        public int getItemCount() {
            if (footView != null && headView != null) {
                return baseAdapter.getItemCount() + 2;
            }
            else if (footView != null || headView != null) {
                return baseAdapter.getItemCount() + 1;
            } else {
                return baseAdapter.getItemCount();
            }

        }
    }


    private class FixedHolder extends ViewHolder {

        public FixedHolder(View itemView) {
            super(itemView);
        }
    }

}
