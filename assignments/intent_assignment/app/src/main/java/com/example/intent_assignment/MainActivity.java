package com.example.intent_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView spotimg;

    Button audi,bently,porsche,bmw;

    int identy;

    int imgind;

    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        spotimg=findViewById(R.id.spotImageView);
        audi = findViewById(R.id.audibtn);
        bently = findViewById(R.id.bentlybtn);
        porsche = findViewById(R.id.porschebtn);
        bmw = findViewById(R.id.bmwbtn);

        audi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, Second_Activity.class);

                intent.putExtra("audi",1);
                startActivityForResult(intent,1);


            }
        });

        bently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, Second_Activity.class);

                intent.putExtra("bently",2);
                startActivityForResult(intent,2);


            }
        });

        porsche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, Second_Activity.class);

                intent.putExtra("porsche",3);
                startActivityForResult(intent,3);


            }
        });

        bmw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, Second_Activity.class);

                intent.putExtra("bmw",4);
                startActivityForResult(intent,4);


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        assert data != null;

        bundle = data.getExtras();

        identy = catch_intent();
        setimage(identy);


    }

    public int catch_intent(){

        if(1==bundle.getInt("ind1")){

            return 1;

        }
        if(2==bundle.getInt("ind1")){

            return 2;

        }
        if(3==bundle.getInt("ind1")){

            return 3;

        }
        if(4==bundle.getInt("ind1")){

            return 4;

        }

        if(1==bundle.getInt("ind2")){

            return 1;

        }
        if(2==bundle.getInt("ind2")){

            return 2;

        }
        if(3==bundle.getInt("ind2")){

            return 3;

        }
        if(4==bundle.getInt("ind2")){

            return 4;

        }


        if(1==bundle.getInt("ind3")){

            return 1;

        }
        if(2==bundle.getInt("ind3")){

            return 2;

        }
        if(3==bundle.getInt("ind3")){

            return 3;

        }
        if(4==bundle.getInt("ind3")){

            return 4;

        }

        if(1==bundle.getInt("ind4")){

            return 1;

        }
        if(2==bundle.getInt("ind4")){

            return 2;

        }
        if(3==bundle.getInt("ind4")){

            return 3;

        }

       else{

            return 4;

        }



    }

    public void setimage(int i){

        if(i==1){

            if(1==bundle.getInt("img1")){

                spotimg.setImageResource(R.drawable.audi1);

            }
            else if(2==bundle.getInt("img2")){

                spotimg.setImageResource(R.drawable.audi2);

            }
            else if(3==bundle.getInt("img3")){

                spotimg.setImageResource(R.drawable.audi3);

            }
            else if(4==bundle.getInt("img4")){

                spotimg.setImageResource(R.drawable.audi4);

            }

        }

        else if(i==2){

            if(1==bundle.getInt("img1")){

                spotimg.setImageResource(R.drawable.bently1);

            }
            else if(2==bundle.getInt("img2")){

                spotimg.setImageResource(R.drawable.bently2);

            }
            else if(3==bundle.getInt("img3")){

                spotimg.setImageResource(R.drawable.bently3);

            }
            else if(4==bundle.getInt("img4")){

                spotimg.setImageResource(R.drawable.bently4);

            }

        }

        else if(i==3){

            if(1==bundle.getInt("img1")){

                spotimg.setImageResource(R.drawable.porsche1);

            }
            else if(2==bundle.getInt("img2")){

                spotimg.setImageResource(R.drawable.porsche2);

            }
            else if(3==bundle.getInt("img3")){

                spotimg.setImageResource(R.drawable.porsche3);

            }
            else if(4==bundle.getInt("img4")){

                spotimg.setImageResource(R.drawable.porsche4);

            }

        }

        else if(i==4){

            if(1==bundle.getInt("img1")){

                spotimg.setImageResource(R.drawable.bmw_7);

            }
            else if(2==bundle.getInt("img2")){

                spotimg.setImageResource(R.drawable.bmw_i7);

            }
            else if(3==bundle.getInt("img3")){

                spotimg.setImageResource(R.drawable.bmw_m5);

            }
            else if(4==bundle.getInt("img4")){

                spotimg.setImageResource(R.drawable.bmw_x3);

            }

        }

    }


}