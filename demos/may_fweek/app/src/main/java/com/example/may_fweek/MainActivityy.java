package com.example.may_fweek;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivityy extends AppCompatActivity{



    LinearLayoutCompat linearLayoutCompat;

    TextView welcomeText;

    TextView displayStudentName;

    EditText unsernameEditText;

    EditText passwordEditText;

    Button btnSubmit;

    @SuppressLint("ResourceAsColor")

    @Override

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        linearLayoutCompat = new LinearLayoutCompat(this);

        LinearLayoutCompat.LayoutParams layoutParamsForContainer = new
                LinearLayoutCompat.LayoutParams(

                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT

        );

        linearLayoutCompat.setLayoutParams(layoutParamsForContainer);
        linearLayoutCompat.setOrientation(LinearLayoutCompat.VERTICAL);
        linearLayoutCompat.setPadding(10,10,10,10);
        linearLayoutCompat.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        LinearLayoutCompat.LayoutParams layoutParamsForViews = new
                LinearLayoutCompat.LayoutParams(

                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
        );

        welcomeText = new TextView(this);
        welcomeText.setText("Welcome to android");
        welcomeText.setTextSize(20.0f);
        welcomeText.setLayoutParams(layoutParamsForViews);

        linearLayoutCompat.addView(welcomeText);

        displayStudentName = new TextView(this);
        displayStudentName.setText("welcome to bitcode");
        displayStudentName.setTextSize(20.0f);
        displayStudentName.setLayoutParams(layoutParamsForViews);
        displayStudentName.setTextColor(R.color.black);

        linearLayoutCompat.addView(displayStudentName);

        unsernameEditText = new EditText(this);
        unsernameEditText.setHint("enter username");
        unsernameEditText.setTextColor(R.color.black);
        unsernameEditText.setLayoutParams(layoutParamsForViews);

        linearLayoutCompat.addView(unsernameEditText);

        passwordEditText = new EditText(this);
        passwordEditText.setHint("enter password");
        passwordEditText.setTextColor(R.color.black);
        passwordEditText.setLayoutParams(layoutParamsForViews);

        linearLayoutCompat.addView(passwordEditText);

        btnSubmit = new Button(this);
        btnSubmit.setText("Submit");
        btnSubmit.setTextColor(R.color.black);
        btnSubmit.setLayoutParams(layoutParamsForViews);

        btnSubmit.setOnClickListener(new BtnOnClickListner());

        linearLayoutCompat.addView(btnSubmit);

        setContentView(linearLayoutCompat);


    }

    class BtnOnClickListner implements View.OnClickListener{

        @Override

        public void onClick(View v){

            if((unsernameEditText.getText().toString().equals("Anshuman")) &&
                    (passwordEditText.getText().toString().equals("pass123"))){

                displayStudentName.setText("welcome "+unsernameEditText.getText());

                Toast.makeText(MainActivityy.this,
                        "login successful!",
                        Toast.LENGTH_LONG)
                        .show();

            }
            else{

                Toast.makeText(MainActivityy.this,
                                "login denied!",
                                Toast.LENGTH_LONG)
                        .show();

            }

        }

    }


}
