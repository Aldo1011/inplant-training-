package com.example.use_of_resources;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.assignment_constriant_layout);

        Resources resources;

        int colorgreen;
        TypedArray colornames;
        float width;
        int [] intvalue;
        String [] names;


        resources = getResources();

        colorgreen= resources.getColor(R.color.blue,null);
        colornames = resources.obtainTypedArray(R.array.colour_name);
        width = resources.getDimension(R.dimen.width);
        intvalue = resources.getIntArray(R.array.int_values);
        names=resources.getStringArray(R.array.student);


        Log.e("space","============================");
        Log.e("tag",colorgreen+"");
        Log.e("space","============================");
        Log.e("tag",width+"");
        Log.e("space","============================");

        for (int values:intvalue
             ) {

            Log.e("tag",values+"");

        }

        Log.e("space","============================");

        for (String name:names) {

            Log.e("tag",name+"");


        }



    }
}