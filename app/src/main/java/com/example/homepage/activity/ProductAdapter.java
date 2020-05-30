package com.example.homepage.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> data;

    public ProductAdapter(Context context, List<Product> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getProdName());
        holder.price.setText(data.get(position).getProdPrice());
        holder.img.setImageResource(data.get(position).getProdImg());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("prod_name", data.get(position).getProdName());
                intent.putExtra("prod_price", data.get(position).getProdPrice());
                intent.putExtra("prod_img", data.get(position).getProdImg());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, price;
        ImageView img;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardview_product);
            name = view.findViewById(R.id.tvProdItemName);
            price = view.findViewById(R.id.tvProdItemPrice);
            img = view.findViewById(R.id.imgProdItem);
        }
    }
}
