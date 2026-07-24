package com.example.a25_05_2026_webservices_retrofitandglide_demo;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ProductService {

    @GET("products")
    Call<APIResponse> getProducts();

    static ProductService getInstance(){

//        ProductService productService = null;
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://dummyjson.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        return retrofit.create(ProductService.class);
    }
}
