package com.example.homepage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homepage.R;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView tvProdName, tvProdPrice;
    private ImageView imgProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        tvProdName = findViewById((R.id.tvProdName));
        tvProdPrice = findViewById((R.id.tvProdPrice));
        imgProd = findViewById(R.id.imgProd);

        String prodName = getIntent().getStringExtra("prod_name");
        String prodPrice = getIntent().getStringExtra("prod_price");
        int image = getIntent().getIntExtra("prod_img", -1);

        tvProdName.setText(prodName);
        tvProdPrice.setText(prodPrice);
        imgProd.setImageResource(image);
    }
}
