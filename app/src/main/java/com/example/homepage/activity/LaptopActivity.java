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

public class LaptopActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private DrawerLayout drawer;
    private ArrayList<SanPham> listLaptop;
    SanPhamAdapter spAdapter;

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
        GetDataLap();
//
//        listLaptop= new ArrayList<>();
//        listLaptop.add(new Product("Macbook Pro 13 Touch Bar i5 1.4GHz/8G/128GB (2019)","39.990.000₫",R.drawable.macbookprotb));
//        listLaptop.add(new Product("MSI GF63 8RC-203VN/I5-8300H ","21.990.000₫",R.drawable.msigf6rd));
//        listLaptop.add(new Product("Acer Nitro AN515-43-R84R/NH.Q5XSV.001 ","16.990.000₫",R.drawable.acernitro52019));
//        listLaptop.add(new Product("Acer Aspire A315 54 34U i3 10110U/4Gb/256Gb/15.6\"HD/Win 10","15.090.000₫",R.drawable.aceraspa315));
//        listLaptop.add(new Product("Asus D570DD-E4027T R5-3500U/4GB/256GB/4GB GTX1050/WIN10","10.490.000 ₫",R.drawable.asusd570dd));
//        listLaptop.add(new Product("HP 15s-du0059TU Pentium N5000/4GB/1TB/WIN10","9.890.000₫",R.drawable.hp15s));

//        ProductAdapter adapter = new ProductAdapter(this, listLaptop);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }
    private void GetDataLap() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://5ed91adb4378690016c6ac70.mockapi.io/api/SP", new Response.Listener<JSONArray>() {
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
                            if (jsonObject.getInt("MaLoaiSanPham") == 4){
                                ID = jsonObject.getInt("IDSanPham");
                                Tensp = jsonObject.getString("TenSanPham");
                                Giasp = jsonObject.getInt("GiaSanPham");
                                Anhsp = jsonObject.getString("HinhAnhSanPham");
                                Motasp = jsonObject.getString("MoTaSanPham");
                                CateID = jsonObject.getInt("MaLoaiSanPham");
                                listLaptop.add(new SanPham(ID,Tensp,Giasp,Anhsp,Motasp,CateID));
                                spAdapter.notifyDataSetChanged();
                            }

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

        listLaptop = new ArrayList<>();
        spAdapter = new SanPhamAdapter(getApplicationContext(),listLaptop);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(spAdapter);
    }

///  ---------   FUNCTION   --------- ///
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
///  ---------  END FUNCTION  --------- ///
