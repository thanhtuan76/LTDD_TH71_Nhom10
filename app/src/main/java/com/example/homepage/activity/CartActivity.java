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
import com.example.homepage.activity.adapter.CartAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recycler_cart;
    Button btnCaculate, btnContinue, quantity;
    CartAdapter cartAdapter;
    TextView txtEmpty, txtTotal;
    ArrayList<Cart> cartArrayList;
    ImageView imgEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Initial();
        EventUltil();
        CheckData();
        catchEventButton();
    }

    private void EventUltil() {
        int total = 0;
        for (int i = 0; i < MainActivity.cartArrayList.size(); i++) {
            total += MainActivity.cartArrayList.get(i).getProdPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotal.setText(decimalFormat.format(total) + " Đ");
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


    private void Initial() {
        recycler_cart = findViewById(R.id.recycle_cart);
        txtEmpty = findViewById(R.id.emptyCart);
//        cartArrayList = new ArrayList<>();
        recycler_cart.setAdapter(cartAdapter);
        recycler_cart.setHasFixedSize(true);
        recycler_cart.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        txtTotal = findViewById((R.id.total));
        btnCaculate = findViewById(R.id.btnCalculate);
        btnContinue = findViewById(R.id.btnContinue);
        cartAdapter = new CartAdapter(CartActivity.this, cartArrayList);
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