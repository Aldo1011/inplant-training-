package com.example.customdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnWay1,btnWay2,btnWay3,nextBtn,dismissBtn;
    TextView welcomeTextView,loginTextView;

    EditText usrEditText,passEditText;

    Dialog dialog;

    Login_dialog_InterfaceWay loginDialogInterfaceWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();

        btnWay1.setOnClickListener(new Customdialogway1listner());

        btnWay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login_Dialog logindialog = new Login_Dialog(MainActivity.this);
                logindialog.show();

            }
        });

        btnWay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 loginDialogInterfaceWay=new Login_dialog_InterfaceWay
                        (MainActivity.this);

                loginDialogInterfaceWay.setCusomDialogInterface(new CustomDialogListner());

                loginDialogInterfaceWay.show();


            }
        });



    }

    public void initViews(){

        btnWay1=findViewById(R.id.btnWay1);
        btnWay2=findViewById(R.id.btnWay2);
        btnWay3=findViewById(R.id.btnWay3);
        welcomeTextView=findViewById(R.id.welcomeTextView);

    }

    public class Customdialogway1listner implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            dialog = new Dialog(MainActivity.this);

            dialog.setContentView(R.layout.login_dialog);

            nextBtn=dialog.findViewById(R.id.nextBtn);
            dismissBtn=dialog.findViewById(R.id.dismissBtn);
            loginTextView = dialog.findViewById(R.id.LoginTextView);
            passEditText= dialog.findViewById(R.id.passEditText);
            usrEditText=dialog.findViewById(R.id.usrEditText);

            dialog.show();


           nextBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Toast.makeText(MainActivity.this,"login successful",
                                   Toast.LENGTH_LONG)
                           .show();

               }
           });

            dismissBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(MainActivity.this,"login cancelled",
                                    Toast.LENGTH_LONG)
                            .show();

                    dialog.dismiss();

                }
            });

        }
    }

    public class CustomDialogListner implements  Login_dialog_InterfaceWay.OnCustomDialogInterface{


        @Override
        public void sucess() {

            Toast.makeText(MainActivity.this,"login denied!",
                    Toast.LENGTH_LONG).show();

        }

        @Override
        public void dismiss() {

            Toast.makeText(MainActivity.this,"login denied!",
                    Toast.LENGTH_LONG).show();

        }
    }

}