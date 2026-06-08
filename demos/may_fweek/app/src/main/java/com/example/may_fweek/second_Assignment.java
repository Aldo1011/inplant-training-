package com.example.may_fweek;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class second_Assignment extends AppCompatActivity {

    TextView welcomeTextView;

    EditText passEditText;

    EditText usrEditText;

    Button login;

    @Override

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);

        welcomeTextView=findViewById(R.id.welcomeTextView);
        passEditText=findViewById(R.id.passEditText);
        usrEditText=findViewById(R.id.usrEditText);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((usrEditText.getText().toString().equals("anshuman")) &&
                        (passEditText.getText().toString().equals("pass123"))){

                    welcomeTextView.setText("welcome "+usrEditText.getText().toString());

                    Toast.makeText(second_Assignment.this,
                            "login scucessful",
                            Toast.LENGTH_SHORT)
                            .show();

                }
                else{

                    Toast.makeText(second_Assignment.this,
                                    "login denied",
                                    Toast.LENGTH_SHORT)
                            .show();

                }


            }
        });

    }

}
