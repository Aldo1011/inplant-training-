package com.example.may_fweek;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home_Activity extends AppCompatActivity implements View.OnClickListener {

    TextView welcomeTextView;

    EditText usrEditText,passEditText;

    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        usrEditText = findViewById(R.id.usrEditText);
        passEditText = findViewById(R.id.passEditText);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if((usrEditText.getText().toString().equals("anshuman")) &&
                (passEditText.getText().toString().equals("pass123"))){

            welcomeTextView.setText("welcome "+usrEditText.getText().toString());

            Toast.makeText(this,
                    "login successful",
                    Toast.LENGTH_LONG)
                    .show();

        }
        else{

            Toast.makeText(this,
                            "login denied",
                            Toast.LENGTH_LONG)
                    .show();


        }

    }
}
