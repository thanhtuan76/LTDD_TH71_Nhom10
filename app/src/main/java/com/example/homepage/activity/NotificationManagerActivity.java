package com.example.homepage.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.homepage.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class NotificationManagerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ListView listNotification;
    private Integer[] title = {R.string.noti1_title, R.string.noti2_title, R.string.noti3_title};
    private Integer[] content = {R.string.noti1_content, R.string.noti2_content, R.string.noti3_content};
    private Integer[] imgId = {R.drawable.s2, R.drawable.s4, R.drawable.s3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_manager);

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
                    case R.id.nav_notification:
                        Intent notificationIntent = new Intent(getApplicationContext(), NotificationManagerActivity.class);
                        startActivity(notificationIntent);
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
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

        // List notification_item
        NotificationAdapter adapter = new NotificationAdapter(this, title, content, imgId);
        listNotification.setAdapter(adapter);

        // Set list event
        listNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NotificationDetailActivity.class);
                switch (position) {
                    case 0:
                        intent.putExtra("title", getString(title[0]));
                        intent.putExtra("content", getString(content[0]));
                        intent.putExtra("img", imgId[0]);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("title", getString(title[1]));
                        intent.putExtra("content", getString(content[1]));
                        intent.putExtra("img", imgId[1]);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("title", getString(title[2]));
                        intent.putExtra("content", getString(content[2]));
                        intent.putExtra("img", imgId[2]);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_cart:
                return true;
            case R.id.nav_notification:
                Intent NotificationManagerIntent = new Intent(this, NotificationManagerActivity.class);
                startActivity(NotificationManagerIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Initial (){
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawerLayout);
        listNotification = findViewById(R.id.listNotification);
    }

}
