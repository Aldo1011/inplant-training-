package com.example.may_fweek;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class assignment1 extends AppCompatActivity {

    LinearLayoutCompat linearLayoutCompat;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    EditText input;

    Button btnSubmit;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);


        linearLayoutCompat=new LinearLayoutCompat(this);

        LinearLayoutCompat.LayoutParams layoutParamsForContainer = new LinearLayoutCompat.LayoutParams(

                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        linearLayoutCompat.setLayoutParams(layoutParamsForContainer);
        linearLayoutCompat.setOrientation(LinearLayoutCompat.VERTICAL);
        linearLayoutCompat.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        LinearLayoutCompat.LayoutParams layoutParamsForView = new LinearLayoutCompat.LayoutParams(

                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        textView1 = new TextView(this);
        textView1.setText("Field 1");
        textView1.setTextSize(20.0f);
        textView1.setBackgroundColor(R.color.purple_500);
        textView1.setTextColor(R.color.white);
        textView1.setLayoutParams(layoutParamsForView);

        linearLayoutCompat.addView(textView1);

        textView2 = new TextView(this);
        textView2.setText("Field 2");
        textView2.setTextSize(20.0f);
        textView2.setBackgroundColor(R.color.purple_500);
        textView2.setTextColor(R.color.white);
        textView2.setLayoutParams(layoutParamsForView);

        linearLayoutCompat.addView(textView2);

        textView3 = new TextView(this);
        textView3.setText("Field 3");
        textView3.setTextSize(20.0f);
        textView3.setBackgroundColor(R.color.purple_500);
        textView3.setTextColor(R.color.white);
        textView3.setLayoutParams(layoutParamsForView);

        linearLayoutCompat.addView(textView3);

        input= new EditText(this);
        input.setHint("choose a field");
        input.setTextSize(20.0f);
        input.setLayoutParams(layoutParamsForView);

        linearLayoutCompat.addView(input);

        btnSubmit = new Button(this);
        btnSubmit.setText("Submit");
        btnSubmit.setTextColor(R.color.black);
        btnSubmit.setLayoutParams(layoutParamsForView);

        btnSubmit.setOnClickListener(new btnOnclickListner());



        linearLayoutCompat.addView(btnSubmit);

        setContentView(linearLayoutCompat);

    }

    class btnOnclickListner implements View.OnClickListener{


        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {


            if(input.getText().toString().equalsIgnoreCase("field 1")){

                textView1.setBackgroundColor(R.color.black);
                textView2.setBackgroundColor(R.color.white);
                textView3.setBackgroundColor(R.color.white);


            }

            else if(input.getText().toString().equalsIgnoreCase("field 2")){

                textView2.setBackgroundColor(R.color.black);
                textView1.setBackgroundColor(R.color.white);
                textView3.setBackgroundColor(R.color.white);

            }
            else if(input.getText().toString().equalsIgnoreCase("field 3")){

                textView3.setBackgroundColor(R.color.black);
                textView1.setBackgroundColor(R.color.white);
                textView2.setBackgroundColor(R.color.white);

            }

        }


    }

}
