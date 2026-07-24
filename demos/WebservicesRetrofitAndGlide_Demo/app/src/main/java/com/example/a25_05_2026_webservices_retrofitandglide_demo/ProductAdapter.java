package com.example.a25_05_2026_webservices_retrofitandglide_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    ArrayList<Product> products;

    public ProductAdapter(ArrayList<Product> productArrayList){

        this.products=productArrayList;

    }


    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView txtViewForProductId, txtViewForProductTitle,txtViewForProductPrice;

        ImageView imgViewForProduct;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);


            txtViewForProductId=itemView.findViewById(R.id.prodictIdTextView);
            txtViewForProductPrice=itemView.findViewById(R.id.prodictPriceTextView);
            txtViewForProductTitle=itemView.findViewById(R.id.prodictTitleTextView);
            imgViewForProduct=itemView.findViewById(R.id.productImageView);

        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View productView = layoutInflater.inflate(R.layout.product_viewholder,null);


        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = products.get(position);
        holder.txtViewForProductId.setText(product.getId()+"");
        holder.txtViewForProductTitle.setText(product.getTitle());
        holder.txtViewForProductPrice.setText(product.getPrice()+"");

        Glide.with(holder.itemView.getContext())
                .load(product.getThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.imgViewForProduct);



    }

    @Override
    public int getItemCount() {
        return 0;
    }




}
