package com.haoxuan.worknote.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.haoxuan.worknote.R;
import com.haoxuan.worknote.fragment.FirstFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private  ActionBarDrawerToggle toggle;
    private LinearLayout baseContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        baseContent= (LinearLayout) findViewById(R.id.base_content);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.openDrawer(Gravity.LEFT);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setCheckedItem(R.id.first);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.first:
                        FragmentManager manager=getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.content,new FirstFragment()).commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.second:
                        drawer.closeDrawers();
                        break;

                }
                return false;
            }
        });
        toggle=new ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        {
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
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content,new FirstFragment()).commit();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.sign_in:
                Intent logInIntent=new Intent(this,SignInActivity.class);
                startActivity(logInIntent);
                break;
            case R.id.save:
                Snackbar.make(baseContent,R.string.save_tip,Snackbar.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
