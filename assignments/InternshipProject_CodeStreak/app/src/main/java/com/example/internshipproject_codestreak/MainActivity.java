package com.example.internshipproject_codestreak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.internshipproject_codestreak.activities.HomeActivity;
import com.example.internshipproject_codestreak.activities.LoginActivity;
import com.google.firebase.appcheck.interop.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();

        Intent intent;

        if(mUser!=null){

            intent =  new Intent(this, HomeActivity.class);

        }else{

            intent = new Intent(this, LoginActivity.class);

        }

        startActivity(intent);
        finish();

    }
}