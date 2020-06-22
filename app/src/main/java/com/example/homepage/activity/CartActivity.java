package com.example.homepage.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homepage.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    RecyclerView recycler_cart;
    Button btnCaculate, btnContinue, quantity;
    CartAdapter cartAdapter;
    TextView txtEmpty, txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Initial();
        EventUltil();
        CheckData();
        catchEventButton();
    }

    private void CheckData() {
        if (MainActivity.cartArrayList.size() <= 0) {
            cartAdapter.notifyDataSetChanged();
            txtEmpty.setVisibility(View.VISIBLE);
            recycler_cart.setVisibility(View.INVISIBLE);
        } else {
            cartAdapter.notifyDataSetChanged();
            txtEmpty.setVisibility(View.INVISIBLE);
            recycler_cart.setVisibility(View.VISIBLE);
        }
    }

    private void EventUltil() {
        int total = 0;
        for (int i = 0; i < MainActivity.cartArrayList.size(); i++) {
            total += MainActivity.cartArrayList.get(i).getProdPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotal.setText(decimalFormat.format(total) + " Đ");
    }

    private void Initial() {
        txtEmpty = findViewById(R.id.emptyCart);

        recycler_cart = findViewById(R.id.recycle_cart);
        cartAdapter = new CartAdapter(CartActivity.this, MainActivity.cartArrayList);
        recycler_cart.setHasFixedSize(true);
        recycler_cart.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recycler_cart.setAdapter(cartAdapter);

        txtTotal = findViewById(R.id.total);
        btnCaculate = findViewById(R.id.btnCalculate);
        btnContinue = findViewById(R.id.btnContinue);
    }
    private void catchEventButton() {
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnContinue) {
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home);
                }
            }
        });
    }
}