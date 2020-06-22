package com.example.homepage.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.R;
import com.example.homepage.activity.adapter.ImageAdapter;
import com.example.homepage.activity.adapter.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    ArrayList<Product> mangsanpham;
    ProductAdapter spAdapter;
    int currentPage = 0;
    int NUM_PAGES = 6;
    Timer timer;
    final long DELAY_MS = 500; //delay in milliseconds before task is to be executed
    final long PERIOD_MS = 2500; // time in milliseconds between successive task executions.


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        recyclerView = view.findViewById(R.id.recyclerViewHome);
        progressBar = view.findViewById(R.id.progressBarHome);

        ImageAdapter imageAdapter = new ImageAdapter(getActivity());
        viewPager.setAdapter(imageAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES-1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        Anhxa();
        GetData();

        return view;
    }

    private void Anhxa() {
        mangsanpham = new ArrayList<>();
        spAdapter = new ProductAdapter(getActivity(),mangsanpham);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(spAdapter);
    }

    private void GetData() {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://5ed91adb4378690016c6ac70.mockapi.io/api/SP", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.INVISIBLE);
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
                            mangsanpham.add(new Product(ID,Tensp,Giasp,Anhsp,Motasp,CateID));
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
}

