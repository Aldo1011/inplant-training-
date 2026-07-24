package com.example.machinetask1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultLauncherKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView mainTextView;
    EditText mainEditText;
    Button editButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

        editButton.setOnClickListener(new EditButtonListner());

    }

    private void initView(){

        mainEditText = findViewById(R.id.mainEditText);
        mainTextView = findViewById(R.id.mainTextView);
        editButton = findViewById(R.id.editButton);

    }

    public class EditButtonListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        EditorDIalogInterface editorDIalogInterface = new EditorDIalogInterface(
                MainActivity.this,
                mainEditText.getText().toString(),
                new EditorDIalogInterface.OutputLisnter() {
                    @Override
                    public void onOutputSend(String outputData) {

                        mainTextView.setText(outputData.toString());

                    }
                }

        );

        editorDIalogInterface.show();

        }



    }

}