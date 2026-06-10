package com.example.demo_dialogue;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView welcomeTextView;

    Button btnAlertDialog,btnDatepickerDialog,btnTimepickerdialog;

    Button btnProgressDialog;

    ProgressDialog progressDialog;

    static int year1;
    static int month1;
    static int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnTimepickerdialog= findViewById(R.id.btnTimepickerDialog);
        btnDatepickerDialog = findViewById(R.id.btnDatepickerDialog);
        btnProgressDialog = findViewById(R.id.btnProgressDialog);

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setTitle("Exam submission");
                alertDialogBuilder.setMessage("do you really want to submit the exam?");

//                way 1

//                alertDialogBuilder.setPositiveButton("Yes",new DialogPositiveBtnClick());
//                alertDialogBuilder.setNegativeButton("No",new DialogNegativeBtnClick());
//                alertDialogBuilder.setNeutralButton("dismiss",new DialogNeutralBtnClick());

//                way 2

                alertDialogBuilder.setPositiveButton("Yes",new DialogCommonBtnCLick());
                alertDialogBuilder.setNeutralButton("dismiss",new DialogCommonBtnCLick());
                alertDialogBuilder.setNegativeButton("NO",new DialogCommonBtnCLick());

                AlertDialog alertDialog =alertDialogBuilder.create();
                alertDialog.show();

            }
        });


        btnDatepickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this
                        , new DatePickerDialoglistner()
                        ,2026
                        ,0
                        ,8);

                datePickerDialog.show();


                String msg =  String.format(getString(R.string.year_d_month_d_day_d),year1,month1+1,day);
                btnDatepickerDialog.setText(msg);

            }
        });


        btnTimepickerdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog= new TimePickerDialog(MainActivity.this
                        ,new TimePickerListner()
                        ,12
                        ,45
                        ,true
                        );
                timePickerDialog.show();
            }
        });

        btnProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] fileUrls={

                        "FileUrl1",
                        "FileUrl1",
                        "FileUrl1",
                        "FileUrl1",

                };

            }
        });

    }

    public class Downloader extends AsyncTask<String,Integer,Float>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("dowload a file");
            progressDialog.setMessage("file downloading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();

        }

        @Override
        protected Float doInBackground(String... Urlfiles) {

            for (Stri:
                 ) {
                
            }


            return 10.3f;
        }
    }


    public class TimePickerListner implements TimePickerDialog.OnTimeSetListener{


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Log.e("tag","hour:"+hourOfDay+" minute"+minute);

        }
    }

    public static class DatePickerDialoglistner implements DatePickerDialog.OnDateSetListener{


        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            Log.e("tag","year"+year+" month"+month+"day"+dayOfMonth);

            year1=year;
            month1=month;
            day=dayOfMonth;

        }
    }

    public class DialogCommonBtnCLick implements DialogInterface.OnClickListener{


        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which){

                case -1:
                    Toast.makeText(MainActivity.this,"yes clicked"+which,Toast.LENGTH_LONG)
                            .show();
                case -2:
                    Toast.makeText(MainActivity.this,"NO clicked"+which,Toast.LENGTH_LONG)
                            .show();
                default:
                    Toast.makeText(MainActivity.this,"dismiss clicked"+which,Toast.LENGTH_LONG)
                            .show();

            }

        }
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