package com.example.a25_05_2026_webservices_retrofitandglide_demo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtViewForProductId, txtViewForProductTitle, txtViewForProductPrice;
    ImageView imgViewForProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        txtViewForProductId = findViewById(R.id.txtViewForProductId);
//        txtViewForProductTitle = findViewById(R.id.txtViewForProductTitle);
//        txtViewForProductPrice = findViewById(R.id.txtViewForProductPrice);
//        imgViewForProduct = findViewById(R.id.imgViewForProduct);

        ProductService productService = ProductService.getInstance();

        productService.getProducts().enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    Product product = response.body().getProducts().get(0);

//                    txtViewForProductId.setText(product.getId() + "");
//                    txtViewForProductTitle.setText(product.getTitle());
//                    txtViewForProductPrice.setText(product.getPrice() + "");
//
//                    //image loading using Glide
//                    Glide.with(MainActivity.this)
//                            .load(product.getThumbnail())
//                            .placeholder(R.drawable.ic_launcher_background)
//                            .centerCrop()
//                            .into(imgViewForProduct);

                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                t.getMessage();
            }
        });

    }
}