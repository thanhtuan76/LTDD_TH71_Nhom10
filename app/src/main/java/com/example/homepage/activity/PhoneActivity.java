package com.example.homepage.activity;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.LinearLayoutManager;
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

public class PhoneActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private DrawerLayout drawer;
    private List<Product> listPhone;

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

        listPhone = new ArrayList<>();
        listPhone.add(new Product("IPhone 11 Pro Max 512GB","43.990.000 ₫",R.drawable.ip11pm));
        listPhone.add(new Product("IPhone Xs Max 256GB","32.990.000 ₫",R.drawable.ipxsm));
        listPhone.add(new Product("Samsung Galaxy S20+","23.990.000 ₫",R.drawable.samsung20));
        listPhone.add(new Product("Samsung Galaxy Note 10 Lite","13.990.000 ₫",R.drawable.ssn10l));
        listPhone.add(new Product("Samsung Galaxy A71","10.490.000 ₫",R.drawable.ssa71));
        listPhone.add(new Product("Huawei Nova 7i","6.990.000 ₫",R.drawable.hn7i));
        listPhone.add(new Product("Vivo Y50 8GB-128GB","6.290.000 ₫",R.drawable.vvy50));
        listPhone.add(new Product("Oppo A31 4GB-128GB","4.990.000 ₫",R.drawable.opa31));
        listPhone.add(new Product("Vsmart Active 3 6GB-64GB","3.990.000 ₫",R.drawable.vsa3));
        listPhone.add(new Product("Realme 5i 4GB-64GB","3.690.000 ₫",R.drawable.rm5i));

        ProductAdapter adapter = new ProductAdapter(this, listPhone);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
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

    private void Initial (){
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawerLayout);
        recyclerView = findViewById(R.id.recyclerview);
    }
}

