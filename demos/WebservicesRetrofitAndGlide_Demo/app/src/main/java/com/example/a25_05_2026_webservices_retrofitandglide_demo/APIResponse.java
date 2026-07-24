package com.example.a25_05_2026_webservices_retrofitandglide_demo;

import java.util.ArrayList;

public class APIResponse {
    ArrayList<Product> products;
    int total;
    int skip;
    int limit;

    public ArrayList<Product> getProducts(){
        return products;
    }
}
