package com.haoxuan.worknote.activity;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoxuan.worknote.R;
import com.haoxuan.worknote.fragment.FirstFragment;
import com.haoxuan.worknote.bean.menu.*;
import com.haoxuan.worknote.widget.HeadRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends DrawerActivity {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private HeadRecyclerView menuList;
    private ActionBarDrawerToggle toggle;
    private LinearLayout baseContent;
    private ArrayList menuData;
//    private ArrayList<com.haoxuan.worknote.bean.menu.MenuItem> menus=new ArrayList<com.haoxuan.worknote.bean.menu.MenuItem>();

    @Override
    protected void onActivityCreateView() {
        super.onActivityCreateView();
        initMenuData();
        setContentView(R.layout.activity_main);
        baseContent = (LinearLayout) findViewById(R.id.base_content);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menuList = (HeadRecyclerView) findViewById(R.id.menu_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        menuList.setLayoutManager(layoutManager);
        menuList.addHeadView(R.layout.drawer_header_layout);
        menuList.setAdapter(new MenuAdapter(menuData));
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
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new FirstFragment()).commit();
    }


    private void initMenuData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("menu_list")) {
                MenuList menuList = (MenuList) bundle.get("menu_list");
                menuData = menuList.getMenus();
            }
        }
    }

    private class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder>
    {
        List<MenuDetail> data;

        MenuAdapter(List data)
        {
            this.data=data;
        }

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MenuViewHolder holder=null;
            View view=null;
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
            holder=new MenuViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder, int position) {
           holder.menuItem.setText(data.get(position).getId());
           holder.menuItem.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Snackbar.make(v,"click menu item",Snackbar.LENGTH_SHORT).show();
               }
           });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }


    private class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView menuItem;

        public MenuViewHolder(View itemView) {
            super(itemView);
            menuItem = (TextView) itemView.findViewById(R.id.menu_item);
        }

    }


    @Override
    protected void onActivityCreated() {
        super.onActivityCreated();
        mSocketTask.execute();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_in:
                Intent logInIntent = new Intent(this, SignInActivity.class);
                startActivity(logInIntent);
                break;
            case R.id.save:
                Snackbar.make(baseContent, R.string.save_tip, Snackbar.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String result) {
        super.onSuccess(result);
    }

    @Override
    public void onError(String message) {
        super.onError(message);
    }


}
