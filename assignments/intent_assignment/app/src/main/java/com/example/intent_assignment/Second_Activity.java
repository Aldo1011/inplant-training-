package com.example.intent_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Second_Activity extends AppCompatActivity {

    ImageView img1,img2,img3,img4;

    int identy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);

        img1 = findViewById(R.id.imgp1ImageView);
        img2 = findViewById(R.id.imgp2ImageView);
        img3 = findViewById(R.id.imgp3ImageView);
        img4 = findViewById(R.id.imgp4ImageView);

        identy=catch_intent();

        setimages(identy);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent();
                intent.putExtra("img1",1);
                intent.putExtra("ind1",identy);
                setResult(1,intent);

                finish();


            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent();
                intent.putExtra("img2",2);
                intent.putExtra("ind2",identy);
                setResult(2,intent);

                finish();


            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent();
                intent.putExtra("img3",3);
                intent.putExtra("ind3",identy);
                setResult(3,intent);

                finish();


            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent();
                intent.putExtra("img4",4);
                intent.putExtra("ind4",identy);
                setResult(4,intent);

                finish();


            }
        });




    }


    public int catch_intent(){

        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        if(4==bundle.getInt("bmw")){

            return 4;


        }
        else if(1==bundle.getInt("audi")){

            return 1;

        }

        else if(2==bundle.getInt("bently")){

            return 2;

        }
        else{

            return 3;

        }


    }

    public void setimages(int i){


        if(i==1){

            img1.setImageResource(R.drawable.audi1);
            img2.setImageResource(R.drawable.audi2);
            img3.setImageResource(R.drawable.audi3);
            img4.setImageResource(R.drawable.audi4);

        }

        else if(i==2){

            img1.setImageResource(R.drawable.bently1);
            img2.setImageResource(R.drawable.bently2);
            img3.setImageResource(R.drawable.bently3);
            img4.setImageResource(R.drawable.bently4);

        }

        else if(i==3){

            img1.setImageResource(R.drawable.porsche1);
            img2.setImageResource(R.drawable.porsche2);
            img3.setImageResource(R.drawable.porsche3);
            img4.setImageResource(R.drawable.porsche4);

        }
        else{

            img1.setImageResource(R.drawable.bmw_7);
            img2.setImageResource(R.drawable.bmw_i7);
            img3.setImageResource(R.drawable.bmw_m5);
            img4.setImageResource(R.drawable.bmw_x3);


        }


    }


}
