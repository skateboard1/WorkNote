package com.haoxuan.worknote.activity;


import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haoxuan.worknote.R;
import com.haoxuan.worknote.fragment.FirstFragment;
import com.haoxuan.worknote.bean.menu.*;
import com.haoxuan.worknote.fragment.dialog.AddNoteDialogFragment;
import com.haoxuan.worknote.widget.HeadRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends DrawerActivity implements AddNoteDialogFragment.OnCreateNoteFinishedListener {

    private HeadRecyclerView menuList;
    private ArrayList<MenuDetail> menuData;
    private LinearLayout addNoteView;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initMenuData();
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        initMenuList();
        showFragment(null);
    }

    private void showFragment(MenuDetail menuDetail) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new FirstFragment()).commit();
    }


    private void initMenuList() {
        menuList = (HeadRecyclerView) findViewById(R.id.menu_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        menuList.setLayoutManager(layoutManager);
        View headView = LayoutInflater.from(this).inflate(R.layout.drawer_header_layout, menuList, false);
        final TextView addNote = (TextView) headView.findViewById(R.id.add_note);
        addNote.setTypeface(Typeface.createFromAsset(getAssets(), "iconfont/addnote.ttf"));
        addNoteView = (LinearLayout) headView.findViewById(R.id.add_note_menu);
        addNoteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNoteMenu();
                Toast.makeText(MainActivity.this, "click add", Toast.LENGTH_SHORT).show();
            }
        });
        menuList.addHeadView(headView);
        menuList.setAdapter(new MenuAdapter(menuData));
    }

    private void showAddNoteMenu() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("menu_list", menuData);
        AddNoteDialogFragment dialogFragment = new AddNoteDialogFragment();
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(), "add_dialog");
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

    @Override
    public void onCreateNoteFinished(MenuDetail detail) {
        menuData.add(detail);
        menuList.getAdapter().notifyDataSetChanged();
        toolbar.setTitle(detail.getName());
        drawer.closeDrawers();
    }

    private class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {
        List<MenuDetail> data;

        MenuAdapter(List data) {
            this.data = data;
        }

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MenuViewHolder holder = null;
            View view = null;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
            holder = new MenuViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder,final int position) {
            holder.menuItem.setText(data.get(position).getName());
            holder.menuItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showFragment(data.get(position));
                    Snackbar.make(v, "click menu item", Snackbar.LENGTH_SHORT).show();
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
//                Snackbar.make(baseContent, R.string.save_tip, Snackbar.LENGTH_SHORT).show();
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

    private void showDetailFragment(MenuDetail menuDetail)
    {
        Fragment fragment=null;

    }

}
