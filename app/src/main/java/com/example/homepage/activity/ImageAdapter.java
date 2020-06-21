package com.example.homepage.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageAdapter extends PagerAdapter {
    private Context context;
    private int[] imgs = {
        R.drawable.s1,
        R.drawable.s2,
        R.drawable.s3,
        R.drawable.s4,
        R.drawable.s5
    };

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(imgs[position]);
        ((ViewPager) container).addView(imageView, 0);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        GetProduct("Apple iPad Pro 11 2020 WiFi 128GB Chính Hãng");
                        break;
                    case 1:
                        GetProduct("Laptop HP ENVY 13-AQ1021TU 8QN79PA");
                        break;
                    case 2:
                        GetProduct("Oppo Reno 2F");
                        break;
                    case 3:
                        GetProduct("Samsung Galaxy S20 Ultra");
                        break;
                    case 4:
                        GetProduct("Samsung Galaxy Tab S6 Lite");
                        break;
                }
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ImageView) object);
    }

    private void GetProduct(final String str) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://5ed91adb4378690016c6ac70.mockapi.io/api/SP", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String Tensp = "";
                    Integer Giasp = 0;
                    String Anhsp = "";
                    String Motasp = "";
                    int CateID = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("IDSanPham");
                            Tensp = jsonObject.getString("TenSanPham");
                            Giasp = jsonObject.getInt("GiaSanPham");
                            Anhsp = jsonObject.getString("HinhAnhSanPham");
                            Motasp = jsonObject.getString("MoTaSanPham");
                            CateID = jsonObject.getInt("MaLoaiSanPham");
                            if (Tensp.toLowerCase().equals(str.toLowerCase())) {
                                Intent prodIntent = new Intent(context, ProductDetailActivity.class);
                                prodIntent.putExtra("thongtinsanpham", new Product(ID, Tensp, Giasp, Anhsp, Motasp, CateID));
                                prodIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(prodIntent);
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
}
