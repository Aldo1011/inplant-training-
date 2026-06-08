package com.example.demo_dialogue;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView welcomeTextView;

    Button btnAlertDialog,btnDatepickerDialog,btnTimepickerdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnTimepickerdialog= findViewById(R.id.btnTimepickerDialog);
        btnDatepickerDialog = findViewById(R.id.btnDatepickerDialog);

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setTitle("Exam submission");
                alertDialogBuilder.setMessage("do you really want to submit the exam?");
                alertDialogBuilder.setPositiveButton("Yes",new DialogPositiveBtnClick());
                alertDialogBuilder.setNegativeButton("No",new DialogNegativeBtnClick());
                alertDialogBuilder.setNeutralButton("dismiss",new DialogNeutralBtnClick());

                AlertDialog alertDialog =alertDialogBuilder.create();
                alertDialog.show();

            }
        });


    }

    public class DialogPositiveBtnClick implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {

            Toast.makeText(MainActivity.this,"yes btn clicked"+which,Toast.LENGTH_LONG).show();


        }
    }

    public class DialogNegativeBtnClick implements DialogInterface.OnClickListener{


        @Override
        public void onClick(DialogInterface dialog, int which) {

            Toast.makeText(MainActivity.this,"no btn clicked"+which,Toast.LENGTH_LONG).show();

        }
    }

    public class DialogNeutralBtnClick implements  DialogInterface.OnClickListener{


        @Override
        public void onClick(DialogInterface dialog, int which) {

            Toast.makeText(MainActivity.this,"dismiss btn clicked"+which,Toast.LENGTH_LONG).show();

        }
    }


}