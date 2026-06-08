package com.example.use_of_intent_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{


    EditText usrEditText,passEditText;

    TextView techNameTextView,techreferenceTextView;

    Button btnNext;

    String techname,techreference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initViews();

        attachListner();

    }

    public void initViews(){

        usrEditText = findViewById(R.id.usrEditText);
        passEditText = findViewById(R.id.passEditText);
        techNameTextView = findViewById(R.id.techTextView);
        techreferenceTextView = findViewById(R.id.techreferenceTextView);
        btnNext = findViewById(R.id.btnNext);

    }

    public void attachListner(){

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, second_activity.class);

                intent.putExtra("usrname",usrEditText.getText().toString());
                intent.putExtra("password",passEditText.getText().toString());
                //startActivity(intent);
                startActivityForResult(intent,1);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        assert data!=null;

        Bundle bundle = data.getExtras();

        techname=bundle.getString("techname");
        techreference=bundle.getString("techreference");

        techNameTextView.setText(techname);
        techreferenceTextView.setText(techreference);

    }
}
