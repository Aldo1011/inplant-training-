package com.example.may_android_dev;

;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class first_app extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutCompat linearLayoutCompat = new
                LinearLayoutCompat(this);

        LinearLayoutCompat.LayoutParams layoutParamsForContainer = new
                LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        linearLayoutCompat.setLayoutParams(layoutParamsForContainer);
        linearLayoutCompat.setOrientation(LinearLayoutCompat.VERTICAL);
        linearLayoutCompat.setPadding(20,20,20,20);
        linearLayoutCompat.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        LinearLayoutCompat.LayoutParams layoutParamsForViews = new
                LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        TextView welcomeTextView = new TextView(this);
        welcomeTextView.setText("Welcome To Android!");
        welcomeTextView.setTextSize(20.0f);
        welcomeTextView.setLayoutParams(layoutParamsForViews);

        linearLayoutCompat.addView(welcomeTextView);

        TextView displayStudentName = new TextView(this);
        displayStudentName.setLayoutParams(layoutParamsForViews);
        displayStudentName.setTextSize(20.0f);
        displayStudentName.setText("Welcome To Bitcode!");
        displayStudentName.setTextColor(R.color.black);

        linearLayoutCompat.addView(displayStudentName);

        EditText usernameEditText = new EditText(this);
        usernameEditText.setLayoutParams(layoutParamsForViews);
        usernameEditText.setHint("Enter username ");
        usernameEditText.setTextColor(R.color.black);

        linearLayoutCompat.addView(usernameEditText);

        EditText passwordEditText = new EditText(this);

        passwordEditText.setTextColor(R.color.black);
        passwordEditText.setHint("Enter password");
        passwordEditText.setLayoutParams(layoutParamsForViews);

        linearLayoutCompat.addView(passwordEditText);

        Button btnSubmit = new Button(this);
        btnSubmit.setText("Submit");
        btnSubmit.setTextColor(R.color.black);
        btnSubmit.setLayoutParams(layoutParamsForViews);

        linearLayoutCompat.addView(btnSubmit);

        setContentView(linearLayoutCompat);
    }
}