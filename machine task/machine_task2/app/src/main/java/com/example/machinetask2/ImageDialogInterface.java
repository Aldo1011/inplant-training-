package com.example.machinetask2;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageSwitcher;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ImageDialogInterface extends Dialog {

    interface ImageOutputInterface{
         void outputFucntion();

    }

    ImageOutputInterface imageOutputInterface;

    ImageSwitcher imageSlider;
    Button exitBtn;
    Spinner delaySpinner;
    CheckBox loopCheckBox;


   ArrayList<String> delay = new ArrayList<>(){{

       add("Slow");
       add("Normal");
       add("Fast");

   }};

   ArrayAdapter<String> adapter= new ArrayAdapter<>(

           getContext(),
           android.R.layout.simple_spinner_item,
           delay

   );



    public ImageDialogInterface(@NonNull Context context,ImageOutputInterface imageOutputInterface){
        super(context);

        this.imageOutputInterface=imageOutputInterface;

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        delaySpinner.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

            }
        });

    }

    private void initViews(){

        imageSlider=findViewById(R.id.imageSlider);
        delaySpinner=findViewById(R.id.delaySpinner);
        loopCheckBox=findViewById(R.id.loopCheckBox);
        exitBtn=findViewById(R.id.exitBtn);

    }






}
