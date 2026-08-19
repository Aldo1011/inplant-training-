package com.example.internshipproject_codestreak.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.internshipproject_codestreak.R;
import com.example.internshipproject_codestreak.models.User;
import com.example.internshipproject_codestreak.repository.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {

    EditText usernameEditText,emailEditTextView,passwordEditText;
    Button sumitSign_Up_Button;
     private FirebaseAuth mAuth;

    private UserRepository userRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_activity);

        initViews();

        sumitSign_Up_Button.setOnClickListener(new SumitBtnListner());

    }

    private void initViews(){

        usernameEditText=findViewById(R.id.UsernameEditView_Sign);
        emailEditTextView=findViewById(R.id.emailEditView_Sign);
        passwordEditText=findViewById(R.id.passwordEditView_Sign);
        sumitSign_Up_Button=findViewById(R.id.submitBtn_sign);
        mAuth=FirebaseAuth.getInstance();
        userRepository = new UserRepository();

    }

    class SumitBtnListner implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            registerUser();

        }
    }

    private void registerUser(){

        String email=emailEditTextView.getText().toString().trim();
        String password=passwordEditText.getText().toString().trim();
        String username=usernameEditText.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            emailEditTextView.setError("Email is required");
            return;

        }

        if(TextUtils.isEmpty(password)){

            passwordEditText.setError("Password is required");
            return;

        }
        if (password.length()<6 ){

            passwordEditText.setError("password should be more than 6 characters");
            return;

        }

        if(TextUtils.isEmpty(username)){

            usernameEditText.setError("Username is required");
            return;

        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "createUserWithEmail:success");


                            FirebaseUser user= mAuth.getCurrentUser();

                            if(user !=null ){

                                User codeStreakUser = new User();

                                codeStreakUser.setUsername(username);
                                codeStreakUser.setEmail(email);

                                userRepository.createUser(user.getUid(), codeStreakUser)
                                        .addOnSuccessListener(aVoid -> {

                                            Toast.makeText(
                                                    SignupActivity.this,
                                                    "Account created successfully!",
                                                    Toast.LENGTH_SHORT
                                            ).show();

                                            Intent intent = new Intent(
                                                    SignupActivity.this,
                                                    LoginActivity.class
                                            );

                                            startActivity(intent);
                                            finish();
                                        })
                                        .addOnFailureListener(e -> {

                                            Toast.makeText(
                                                    SignupActivity.this,
                                                    "Account created, but profile setup failed.",
                                                    Toast.LENGTH_LONG
                                            ).show();

                                });

                            }




                        } else {

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }


}
