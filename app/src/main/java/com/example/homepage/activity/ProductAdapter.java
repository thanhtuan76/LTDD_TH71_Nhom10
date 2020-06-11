//package com.example.homepage.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.media.Image;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.homepage.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
//    Context context;
//    ArrayList<Product> data;
//    public class ItemHolder extends RecyclerView.ViewHolder{
//        public TextView name, price;
//        public ImageView img;
//        CardView cardView;
//
//        public ItemHolder(View view) {
//            super(view);
//            cardView = view.findViewById(R.id.cardview_product);
//            name = view.findViewById(R.id.texttensp);
//            price = view.findViewById(R.id.textgiasp);
//            img = view.findViewById(R.id.imgviewsp);
//        }
//    }
//
//    public ProductAdapter(Context context, List<Product> data) {
//        this.context = context;
//        this.data = data;
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, final int position) {
//        holder.name.setText(data.get(position).getProdName());
//        holder.price.setText(data.get(position).getProdPrice());
//        holder.img.setImageResource(data.get(position).getProdImg());
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProductDetailActivity.class);
//                intent.putExtra("prod_name", data.get(position).getProdName());
//                intent.putExtra("prod_price", data.get(position).getProdPrice());
//                intent.putExtra("prod_img", data.get(position).getProdImg());
//                context.startActivity(intent);
//            }
//        });
//    }
//
//    @NonNull
//    @Override
//    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//
//}
