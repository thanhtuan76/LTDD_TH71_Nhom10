package com.example.homepage.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
    Context context;
    ArrayList<Product> arrProduct;

    public ProductAdapter(Context context, ArrayList<Product> arrProduct) {
        this.context = context;
        this.arrProduct = arrProduct;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView name, price;
        public ImageView img;
        CardView cardView;

        public ItemHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardview_product);
            name = view.findViewById(R.id.texttensp);
            price = view.findViewById(R.id.textgiasp);
            img = view.findViewById(R.id.imgviewsp);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ProductDetailActivity.class);
                    intent.putExtra("thongtinsanpham", arrProduct.get(getAdapterPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product, parent, false);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product sp = arrProduct.get(position);
        holder.name.setText(sp.getProdName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.price.setText(decimalFormat.format(sp.getProdPrice()) + " Đ");
        Picasso.with(context).load(sp.getProdImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return arrProduct.size();
    }

}

