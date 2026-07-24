package com.example.machinetask1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class EditorDIalogInterface extends Dialog {

    private TextView editTextView;
    private RadioGroup editRadioGroup;
    private Button finalButton;
    private CheckBox reverseCheckBox;


    public interface OutputLisnter {

         void onOutputSend(String outputData);

    }

    private String inputData;
    private OutputLisnter outputLisnter;

    public EditorDIalogInterface(@NonNull Context context, String inputData, OutputLisnter outputLisnter) {
        super(context);

        this.inputData=inputData;
        this.outputLisnter=outputLisnter;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.texteditordialog);

        intiViews();

        editTextView.setText(inputData);

        editRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {

                RadioButton radioButton = findViewById(checkedId);
                String opt1 = getContext().getString(R.string.upper_case);
                String opt2 = getContext().getString(R.string.lower_case);


                if(radioButton.getText().toString().equals(opt1)){

                    editTextView.setText(editTextView.getText().toString().toUpperCase());

                }

                else if(radioButton.getText().toString().equals(opt2)){

                    editTextView.setText(editTextView.getText().toString().toLowerCase());

                }

                else {

                    editTextView.setText(capitalizeAllWords(editTextView.getText().toString()));

                }


            }
        });

        reverseCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tmp=editTextView.getText().toString();
                String reverse=new StringBuilder(tmp).reverse().toString();
                editTextView.setText(reverse);

            }
        });


        finalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                outputLisnter.onOutputSend(editTextView.getText().toString());

                dismiss();

            }
        });

    }

    private void intiViews(){

        editTextView = findViewById(R.id.editTextView);
        editRadioGroup = findViewById(R.id.editorRadioGroup);
        reverseCheckBox = findViewById(R.id.reverseCheckBox);
        finalButton = findViewById(R.id.submitButton);


    }

    public String capitalizeAllWords(String text) {

        if (text == null || text.trim().isEmpty()) {
            return text;
        }


        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {

                result.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }


        return result.toString().trim();
    }





}
