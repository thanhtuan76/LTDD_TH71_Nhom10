package com.example.homepage.activity;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context mContext;
    private List<Product> mData;

    public ProductAdapter(Context mContext, List<Product> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvProdItemName.setText(mData.get(position).getProdName());
        holder.tvProdItemPrice.setText(mData.get(position).getProdPrice());
        holder.imgProdItem.setImageResource(mData.get(position).getProdImg());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(mContext, ProductDetailActivity.class);
                productIntent.putExtra("prod_name", mData.get(position).getProdName());
                productIntent.putExtra("prod_img", mData.get(position).getProdImg());
                productIntent.putExtra("prod_price", mData.get(position).getProdPrice());
                mContext.startActivity(productIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvProdItemName;
        TextView tvProdItemPrice;
        ImageView imgProdItem;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardview_product);
            tvProdItemName = view.findViewById(R.id.tvProdItemName);
            tvProdItemPrice = view.findViewById(R.id.tvNotiItemTitle);
            imgProdItem = view.findViewById(R.id.imgProdItem);
        }
    }
}
