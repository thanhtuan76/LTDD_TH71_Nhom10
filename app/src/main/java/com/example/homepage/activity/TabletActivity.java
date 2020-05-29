package com.example.homepage.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.homepage.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class TabletActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private List<Product> listTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet);

        Initial();
        setSupportActionBar(toolbar);
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
//            case R.id.nav_others:
//                intent.setClass(this, PhoneActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.nav_watch:
//                intent.setClass(this, PhoneActivity.class);
//                startActivity(intent);
//                break;
                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        listTablet = new ArrayList<>();
        listTablet.add(new Product("IPad Pro 12.9 2020  WI-FI 4G 1TB ", "51.990.000 ₫", R.drawable.ipadp129));
        listTablet.add(new Product("IPad Pro 11 WI-FI 512GB ", "31.990.000 ₫", R.drawable.ipadp11));
        listTablet.add(new Product("Samsung Galaxy Tab S6  (2019) ", "18.490.000 ₫", R.drawable.ssgalaxytabs6));
        listTablet.add(new Product("iPad Air 3 10.5 Wi-Fi 64GB  ", "13.990.000 ₫", R.drawable.ipadair4g3c));
        listTablet.add(new Product("iPad 2019 10.2 Wi-Fi  128GB ", "11.990.000 ₫", R.drawable.ipadp10129));
        listTablet.add(new Product("Huawei MediaPad M5 Lite  64GB ", "7.990.000 ₫", R.drawable.hwm5l));
        listTablet.add(new Product("Samsung Galaxy Tab A  Plus 8.0 (2019)  ", "6.990.000 ₫", R.drawable.sstabaplus8));
        listTablet.add(new Product("Huawei MediaPad T5 10 ", "4.990.000 ₫", R.drawable.hwt5));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
        ProductAdapter myAdapter = new ProductAdapter(this, listTablet);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));
        myrv.setAdapter(myAdapter);

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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void Initial (){
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawerLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        inflater.inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_buy:
                return true;
            case R.id.action_noti:
                Intent NotificationManagerIntent = new Intent(this, NotificationManagerActivity.class);
                startActivity(NotificationManagerIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
