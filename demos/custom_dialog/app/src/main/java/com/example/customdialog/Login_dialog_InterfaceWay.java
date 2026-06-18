package com.example.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class Login_dialog_InterfaceWay extends Dialog {

    TextView loginTextView;
    EditText usrEditText,passEditText;

    Button loginBtn,dismissBtn;


    public interface OnCustomDialogInterface{

         void sucess();

         void dismiss();

    }

    OnCustomDialogInterface onCustomDialogInterface;

    public void setCusomDialogInterface(OnCustomDialogInterface onCustomDialogInterface){

        this.onCustomDialogInterface = onCustomDialogInterface;

    }

    public Login_dialog_InterfaceWay(@NonNull Context context) {
        super(context);

        setContentView(R.layout.login_dialog);

        initViews();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               onCustomDialogInterface.sucess();

                dismiss();

            }
        });

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onCustomDialogInterface.dismiss();

                dismiss();

            }
        });


    }

    public void initViews(){

        loginTextView = findViewById(R.id.LoginTextView);
        usrEditText = findViewById(R.id.usrEditText);
        passEditText = findViewById(R.id.passEditText);
        loginBtn = findViewById(R.id.nextBtn);
        dismissBtn = findViewById(R.id.dismissBtn);


    }



}
