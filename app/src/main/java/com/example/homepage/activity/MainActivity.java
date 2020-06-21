package com.example.homepage.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.ActionItemBadgeAdder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.font.TextAttribute;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private BottomNavigationView bottomNav;
    private String strUsername;
    private TextView tvSearch;

/// ------------------------------------------------------ ///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initial();
        setSupportActionBar(toolbar);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentTransaction.replace(R.id.fragment_container, homeFragment).addToBackStack(null).commit();
                        break;
                    case R.id.nav_cart:
                        CartFragment cartFragment = new CartFragment();
                        fragmentTransaction.replace(R.id.fragment_container, cartFragment).addToBackStack(null).commit();
                        break;
                    case R.id.nav_info:
                        InfoFragment infoFragment = new InfoFragment();
                        if (strUsername != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("user_name", strUsername);
                            infoFragment.setArguments(bundle);
                        }
                        fragmentTransaction.replace(R.id.fragment_container, infoFragment).addToBackStack(null).commit();
                        break;
                }
                return true;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_phone:
                        Intent phoneIntent = new Intent(getApplicationContext(), PhoneActivity.class);
                        startActivity(phoneIntent);
                        break;
                    case R.id.nav_latop:
                        Intent laptopIntent = new Intent(getApplicationContext(), LaptopActivity.class);
                        startActivity(laptopIntent);
                        break;
                    case R.id.nav_tablet:
                        Intent tabletIntent = new Intent(getApplicationContext(), TabletActivity.class);
                        startActivity(tabletIntent);
                        break;
                    case R.id.nav_watch:
                        Intent watchIntent = new Intent(getApplicationContext(), WatchActivity.class);
                        startActivity(watchIntent);
                        break;
                    case R.id.nav_notification:
                        Intent notificationIntent = new Intent(getApplicationContext(), NotificationManagerActivity.class);
                        startActivity(notificationIntent);
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Navigation bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setLogo(R.drawable.logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("");
    }

/// ------------------------------------------------------ ///

    ///  ---------   FUNCTION   --------- ///

    private void Initial() {
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawerLayout);
        bottomNav = findViewById(R.id.bottom_nav);
        tvSearch = findViewById(R.id.tvSearch);
        strUsername = getIntent().getStringExtra("user_name");

        // Set Home Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.add(R.id.fragment_container, homeFragment).addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_noti:
                Intent NotificationManagerIntent = new Intent(this, NotificationManagerActivity.class);
                startActivity(NotificationManagerIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    ///  ---------  END FUNCTION  --------- ///
}
