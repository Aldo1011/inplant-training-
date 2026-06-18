package com.example.recyclerview_demo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initProductArrayList();
        initViews();


    }

    public void initProductArrayList(){

        productArrayList=new ArrayList<Product>();
        for (int i = 0; i < 40; i++) {

            productArrayList.add(new Product(

                    i+100,
                    "product"+i+1,
                    i*1000+500

            ));

        }

    }

    public void initViews(){

        recyclerView=findViewById(R.id.recylerViewForProducts);
        productAdapter=new ProductAdapter(productArrayList);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
        );

    }

}