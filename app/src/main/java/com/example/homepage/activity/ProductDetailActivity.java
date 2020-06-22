package com.example.homepage.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homepage.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView tvProdName, tvProdPrice, mota;
    private ImageView imgProd;
    private Button btnBuy;

    int id = 0;
    String TenCT = "";
    int Gia = 0;
    String HinhCT = "";
    String MotaCT = "";
    int idloaisp = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

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

        GetInfo();
        EventButton();
    }

    private void EventButton() {
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.cartArrayList.size() > 0){
                    int quant = 1;
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.cartArrayList.size(); i++){
                        if (MainActivity.cartArrayList.get(i).getProdID() == id){
                            MainActivity.cartArrayList.get(i).setQuantity(MainActivity.cartArrayList.get(i).getQuantity() + quant);
                            if (MainActivity.cartArrayList.get(i).getQuantity() >= 10) {
                                MainActivity.cartArrayList.get(i).setQuantity(10);
                            }
                            MainActivity.cartArrayList.get(i).setProdPrice(Gia * MainActivity.cartArrayList.get(i).getQuantity());
                            exists = true;
                        }
                    }
                    if (!exists){
                        int quantity = 1;
                        int total = Gia;
                        MainActivity.cartArrayList.add(new Cart(id, TenCT, total, HinhCT, quantity, idloaisp));
                    }

                }else {
                    int quantity = 1;
                    int total = Gia;
                    MainActivity.cartArrayList.add(new Cart(id, TenCT, total, HinhCT, quantity, idloaisp));
                }
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }


    public void GetInfo (){
        Product product = (Product) getIntent().getSerializableExtra("thongtinsanpham");
        id = product.getProdID();
        TenCT =  product.getProdName();
        Gia = product.getProdPrice();
        HinhCT = product.getProdImg();
        MotaCT = product.getProdDes();
        idloaisp = product.getProdCateID();
        tvProdName.setText(TenCT);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvProdPrice.setText("Giá: " + decimalFormat.format(Gia) + " Đ");
        mota.setText(MotaCT);
        Picasso.with(getApplicationContext()).load(HinhCT).into(imgProd);
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
            case R.id.action_cart:
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
        tvProdName = findViewById((R.id.tvProdName));
        tvProdPrice = findViewById((R.id.tvProdPrice));
        mota = findViewById(R.id.mota);
        imgProd = findViewById(R.id.imgProd);
        btnBuy = findViewById(R.id.btnBuy);
    }
}
