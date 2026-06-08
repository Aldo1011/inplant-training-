package com.example.use_of_intent_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class second_activity extends AppCompatActivity {

    TextView usrTextView,passTextView;

    EditText techNAmeEditText,techreferenceEditText;

    Button btnBack;

    String usr,pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        initview();

        extractData();

        attachListner();

    }

    public void initview(){

        usrTextView = findViewById(R.id.usrTextView);
        passTextView = findViewById(R.id.passTextView);
        techNAmeEditText = findViewById(R.id.techNAmeEditText);
        techreferenceEditText= findViewById(R.id.techreferenceEditText);
        btnBack = findViewById(R.id.btnBack);


    }

    public void attachListner(){

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();

                i.putExtra("techname",techNAmeEditText.getText().toString());
                i.putExtra("techreference",techreferenceEditText.getText().toString());

                setResult(1,i);

                finish();//this method is an activity method this method pops the activity from the
                         //navigation stack thus returning to the first activity or the previous activity


            }
        });

    }

    public void extractData(){

        Intent i = getIntent();
        usr=i.getStringExtra("usrname");
        pass=i.getStringExtra("password");
        usrTextView.setText(usr);
        passTextView.setText(pass);

    }



}
