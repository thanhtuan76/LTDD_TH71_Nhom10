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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TabletActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private DrawerLayout drawer;
    //private List<Product> listTablet;
    private ArrayList<SanPham> listTab;
    private SanPhamAdapter spAdapter;

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


        Anhxa();
        GetDataTab();
    }

    private void GetDataTab() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://5ed91adb4378690016c6ac70.mockapi.io/api/Tablets", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int ID = 0;
                    String Tensp = "";
                    Integer Giasp = 0;
                    String Anhsp = "";
                    String Motasp = "";
                    int CateID = 0;
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("IDSanPham");
                            Tensp = jsonObject.getString("TenSanPham");
                            Giasp = jsonObject.getInt("GiaSanPham");
                            Anhsp = jsonObject.getString("HinhAnhSanPham");
                            Motasp = jsonObject.getString("MoTaSanPham");
                            CateID = jsonObject.getInt("MaLoaiSanPham");
                            listTab.add(new SanPham(ID,Tensp,Giasp,Anhsp,Motasp,CateID));
                            spAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void Anhxa() {

        listTab = new ArrayList<>();
        spAdapter = new SanPhamAdapter(getApplicationContext(),listTab);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(spAdapter);
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
            case R.id.action_noti:
                Intent NotificationManagerIntent = new Intent(this, NotificationManagerActivity.class);
                startActivity(NotificationManagerIntent);
                return true;
            case R.id.action_home:
                Intent ReturnHome = new Intent(this, MainActivity.class);
                startActivity(ReturnHome);
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
