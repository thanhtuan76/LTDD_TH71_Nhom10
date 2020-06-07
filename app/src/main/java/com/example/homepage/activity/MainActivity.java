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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private BottomNavigationView bottomNav;
<<<<<<< HEAD
    private boolean isFrameDisplayed = false;

    /// ------------------------------------------------------ ///
    //View flipper
    int[] imgs = {
            R.drawable.s1,
            R.drawable.s2,
            R.drawable.s3,
            R.drawable.s4,
            R.drawable.s5,
            R.drawable.s6
    };
=======

/// ------------------------------------------------------ ///
>>>>>>> ceb6824843184e58470070eebed660f31539b3bb

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initial();
        setSupportActionBar(toolbar);

<<<<<<< HEAD


=======
>>>>>>> ceb6824843184e58470070eebed660f31539b3bb
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
                    case R.id.nav_notification:
                        Intent notificationIntent = new Intent(getApplicationContext(), NotificationManagerActivity.class);
                        startActivity(notificationIntent);
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        // Create notification channel
        createNotificationChannel();

        // Add notification
        addNotification(1, R.string.noti1_title, R.string.noti1_content, R.drawable.s2);
        addNotification(3, R.string.noti3_title, R.string.noti3_content, R.drawable.s3);
        addNotification(2, R.string.noti2_title, R.string.noti2_content, R.drawable.s4);


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

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.noti_channel_name);
            String description = getString(R.string.noti_channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("NotificationChannel", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification(int id, int notiTitle, int notiContent, int img) {
        String title = getString(notiTitle);
        String content = getString(notiContent);
        Bitmap picture = BitmapFactory.decodeResource(getResources(), img);

        Intent notificationIntent = new Intent(this, NotificationDetailActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtra("img", img);
        notificationIntent.putExtra("title", title);
        notificationIntent.putExtra("content", content);
        PendingIntent pendIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "NotificationChannel")
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(picture)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, builder.build());
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
