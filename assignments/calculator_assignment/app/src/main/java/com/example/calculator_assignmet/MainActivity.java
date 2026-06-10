package com.example.calculator_assignmet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    row 1
    Button btn1,btn2,btn3,btnclear;

//  row 2
    Button btn4,btn5,btn6,btnx;

//    row 3
    Button btn7,btn8,btn9,btnplus;

//    row 4
    Button btndiv,btnmul,btnmin,btn0;

//    row 5
    Button btnequal;


    TextView calview1,calview2;

    String click;

    boolean isoperatoron=false;

    String operator;

    int number1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initview();

        attachlistner();




    }

    public void initview(){

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
        btn0=findViewById(R.id.btn0);

        btnclear=findViewById(R.id.btnClear);
        btnx=findViewById(R.id.btnX);
        btnequal=findViewById(R.id.btnequal);

        btnplus=findViewById(R.id.btnplus);
        btndiv=findViewById(R.id.btndiv);
        btnmin=findViewById(R.id.btnminus);
        btnmul=findViewById(R.id.btnmul);

        calview1=findViewById(R.id.calViewText1);
        calview2=findViewById(R.id.calViewText2);


    }

    public void attachlistner(){

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnplus.setOnClickListener(this);
        btnmin.setOnClickListener(this);
        btndiv.setOnClickListener(this);
        btnequal.setOnClickListener(this);
        btnmul.setOnClickListener(this);
        btnclear.setOnClickListener(this);
        btnx.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Button btnclicked=(Button) v;

        click=btnclicked.getText().toString();

        switch (click){

            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":

                onClickNumber();
                break;

            case "/":
            case "+":
            case "-":
            case "x":
                onClickOperator();
                break;

            case "c":
            case "D":
                Log.e("tag",click);
                onClickClear();

                break;

            case "=":
                onCLickEqual();
                break;

        }

    }

    public void onClickNumber(){

        calview2.append(click);


    }

    public void onClickOperator(){

        String tmp=calview2.getText().toString();

        if(!tmp.isEmpty()) {

            if (isoperatoron) {

                Toast.makeText(MainActivity.this, "error",
                        Toast.LENGTH_LONG).show();

            } else {

                number1 = Integer.parseInt(tmp);
                operator = click;
                calview1.append(calview2.getText().toString());
                calview1.append(click);
                calview2.setText("");
                isoperatoron = true;

            }
        }
        else{

            Toast.makeText(MainActivity.this, "error",
                    Toast.LENGTH_LONG).show();

        }

    }

    public void onClickClear(){


        switch (click){

            case "c":

                calview1.setText("");
                calview2.setText("");
                number1=0;
                isoperatoron=false;
                break;

            case "D":

                String tmp=calview2.getText().toString();
                if(!tmp.isEmpty()){

                    String n=tmp.substring(0,tmp.length()-1);
                    calview2.setText(n);

                }

                break;

            default:
                Toast.makeText(MainActivity.this, "error",
                        Toast.LENGTH_LONG).show();
                break;
        }


    }


    @SuppressLint("SetTextI18n")
    public void onCLickEqual(){

        calview1.setText("");
        int tmp;
        int ans;

        switch (operator){

            case "+":
                tmp=Integer.parseInt(calview2.getText().toString());
                ans=tmp+number1;
                calview2.setText(""+ans);

                break;

            case "-":
                tmp=Integer.parseInt(calview2.getText().toString());
                ans=number1-tmp;
                calview2.setText(""+ans);

                break;

            case "x":
                tmp=Integer.parseInt(calview2.getText().toString());
                ans=tmp*number1;
                calview2.setText(""+ans);

                break;

            case "/":
                tmp=Integer.parseInt(calview2.getText().toString());
                ans=number1/tmp;
                calview2.setText(""+ans);

            default:
                Toast.makeText(MainActivity.this, "error",
                        Toast.LENGTH_LONG).show();
                break;

        }

        isoperatoron=false;


    }




}