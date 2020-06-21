package com.example.homepage.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.R;
import com.example.homepage.activity.adapter.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewFlipper view_flipper;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    ArrayList<Product> mangsanpham;
    ProductAdapter spAdapter;

    //View flipper
    int[] imgs = {

            R.drawable.s1,
            R.drawable.s2,
            R.drawable.s3,
            R.drawable.s4,
            R.drawable.s5,
            R.drawable.s6
    };

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view_flipper = view.findViewById(R.id.v_flipper);
        recyclerView = view.findViewById(R.id.recyclerViewHome);
        progressBar = view.findViewById(R.id.progressBarHome);

        //View flipper
        for (int i = 0; i < imgs.length; i++) {
            flip_img(imgs[i]);
        }

        Anhxa();
        GetData();

        return view;
    }

    //View flipper function
    public void flip_img(int id) {
        ImageView view = new ImageView(getActivity());
        view.setImageResource(id);
        view_flipper.addView(view);
        view_flipper.setFlipInterval(4000);
        view_flipper.setAutoStart(true);
        view_flipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        view_flipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
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

