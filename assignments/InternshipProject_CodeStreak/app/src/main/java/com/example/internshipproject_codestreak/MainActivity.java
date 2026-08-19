package com.example.internshipproject_codestreak;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.internshipproject_codestreak.activities.HomeActivity;
import com.example.internshipproject_codestreak.activities.LoginActivity;
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