package com.example.homepage.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.R;
import com.example.homepage.activity.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    List<Cart> cartArrayList;

    public CartAdapter(Context context, ArrayList<Cart> cartArrayList) {
        this.context = context;
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.cart_items, parent, false);

        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
        holder.name.setText(cartArrayList.get(position).prodName);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.price.setText("Giá : " + decimalFormat.format(cartArrayList.get(position).getProdPrice()) + " VND");
        Picasso.with(context).load(cartArrayList.get(position).getProdImg()).fit().into(holder.img_product);
        holder.btnValues.setText(cartArrayList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name, price;
        public ImageView img_product;
        public Button btnMinus, btnValues, btnPlus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name =  (TextView) itemView.findViewById(R.id.txtProName);
            price = (TextView) itemView.findViewById(R.id.txtProPrice);
            img_product = (ImageView) itemView.findViewById(R.id.imgProdCart);
            btnMinus =  (Button) itemView.findViewById(R.id.counterDE);
            btnValues = (Button) itemView.findViewById(R.id.counterValue);
            btnPlus = (Button) itemView.findViewById(R.id.counterIN);
        }
    }

}
