package com.example.homepage.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    ArrayList<Cart> cartArrayList;

    public CartAdapter(Context context, ArrayList<Cart> cartArrayList) {
        this.context = context;
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, null);
        CartViewHolder cartViewHolder = new CartViewHolder(v);
        v.setTag(cartViewHolder);
        return cartViewHolder;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartArrayList.get(position);
        holder.name.setText(cart.getProdName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.price.setText("Giá: " + decimalFormat.format(cart.getProdPrice()) + " Đ");
        Picasso.with(context).load(cart.getProdImg()).fit().into(holder.img_product);
        holder.btnValue.setText(String.format("%d",cart.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name, price;
        public ImageView img_product;
        public Button btnMinus, btnValue, btnPlus;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtProName);
            price = itemView.findViewById(R.id.txtProPrice);
            img_product = itemView.findViewById(R.id.imgProdCart);
            btnMinus = itemView.findViewById(R.id.counterDE);
            btnValue = itemView.findViewById(R.id.counterValue);
            btnPlus = itemView.findViewById(R.id.counterIN);
        }
    }
}
