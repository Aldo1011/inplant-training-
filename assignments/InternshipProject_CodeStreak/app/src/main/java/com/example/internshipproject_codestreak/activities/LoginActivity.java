package com.example.internshipproject_codestreak.activities;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.internshipproject_codestreak.MainActivity;
import com.example.internshipproject_codestreak.R;
import com.example.internshipproject_codestreak.viewmodel.CodeExecutionEngine;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth; // firebase authenthication object instance

    Button loginBtn;
    EditText editUsername;
    EditText editPassword;

    TextView sign_upTextView;

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initViews();

        mAuth = FirebaseAuth.getInstance(); // initializeation of firebase auth

        loginBtn.setOnClickListener(new loginBtnListner());

        sign_upTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2=new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent2);
                finish();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser =
                mAuth.getCurrentUser();

        if (currentUser != null) {

            Intent homeIntent =
                    new Intent(
                            LoginActivity.this,
                            HomeActivity.class
                    );

            startActivity(homeIntent);
            finish();
        }
    }

    private void initViews(){

        loginBtn=findViewById(R.id.loginButton);
        editUsername=findViewById(R.id.edtUsername);
        editPassword=findViewById(R.id.edtPassword);
        sign_upTextView=findViewById(R.id.sign_upTextView);

    }

    private class loginBtnListner implements View.OnClickListener {


        @Override
        public void onClick(View v) {

            String editPass=editPassword.getText().toString().trim();
            String editUser=editUsername.getText().toString().trim();

            FirebaseUser curretUser=mAuth.getCurrentUser();

            if(curretUser!=null){

                startActivity(intent);

            }

            if(editPass.isEmpty() || editUser.isEmpty()){

                Toast.makeText(
                        LoginActivity.this,
                                "Please fill ALL the Fields",
                                LENGTH_LONG)
                        .show();
                return;
            }

            mAuth.signInWithEmailAndPassword(editUser,editPass).addOnCompleteListener(
                    LoginActivity.this,
                    new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        Intent intent1=new Intent(LoginActivity.this,HomeActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity(intent1);

                    }else {

                        String error=task.getException()!=
                                null ? task.getException().getMessage() : "Auth Failed";

                        Toast.makeText(LoginActivity.this,
                                "Authentication failed"+error,
                                LENGTH_LONG).show();

                    }

                }
            });




        }
    }


}
