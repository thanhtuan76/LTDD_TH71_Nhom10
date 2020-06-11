package com.example.homepage.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewFlipper view_flipper;
    private RecyclerView recyclerView;
//    private List<Product> listSP;
//    private String JSON_URL = "https://5ed91adb4378690016c6ac70.mockapi.io/api/SP";

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

        //View flipper
        for (int i = 0; i < imgs.length; i++) {
            flip_img(imgs[i]);
        }

//        listSP = new ArrayList<>();
//        GetData();

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

//    private void GetData() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,JSON_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            Toast.makeText(getActivity(),"OK",Toast.LENGTH_SHORT).show();
//                            JSONArray ProductArray = new JSONArray(response);
//                            int IDSanPham = 0;
//                            String TenSanPham = "";
//                            String HinhAnhSanPham = "";
//                            Integer GiaSanPham = 0;
//                            String MoTaSanPham = "";
//
//                            for (int i = 0; i < ProductArray.length(); i++) {
//
//                                JSONObject jsonObject = ProductArray.getJSONObject(i);
//                                IDSanPham = jsonObject.getInt("IDSanPham");
//                                TenSanPham = jsonObject.getString("TenSanPham");
//                                HinhAnhSanPham = jsonObject.getString("HinhAnhSanPham");
//                                GiaSanPham = jsonObject.getInt("GiaSanPham");
//                                MoTaSanPham = jsonObject.getString("MoTaSanPham");
//                                listSP.add(new Product(IDSanPham, TenSanPham, GiaSanPham, HinhAnhSanPham, MoTaSanPham));
//                            }
//                            ProductAdapter adapter = new ProductAdapter(getActivity(),listSP);
//                            recyclerView.setAdapter(adapter);
//                            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//                        }
//                        catch (JSONException e){
//                            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(stringRequest);
//    }
}

