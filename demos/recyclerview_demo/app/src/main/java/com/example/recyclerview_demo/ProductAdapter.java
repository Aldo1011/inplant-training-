package com.example.recyclerview_demo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    ArrayList<Product> products;

    public ProductAdapter(ArrayList<Product> productArrayList){

        this.products=productArrayList;

    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView=new TextView(itemView.getContext());


        }

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TextView textView=new TextView(parent.getContext());
        ViewGroup.LayoutParams layoutParams= new ViewGroup.LayoutParams(

                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT

        );

        textView.setLayoutParams(layoutParams);
        textView.setTextSize(30.f);

        return new ProductViewHolder(textView);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        TextView textView=(TextView) holder.itemView;
        textView.setText(products.get(position).getProductName());


    }

    @Override
    public int getItemCount() {

        return products.size();

    }
}
