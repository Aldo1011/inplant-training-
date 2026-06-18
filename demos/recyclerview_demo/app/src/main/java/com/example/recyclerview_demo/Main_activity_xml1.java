package com.example.recyclerview_demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Main_activity_xml1 extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    ArrayList<Student> studentArrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initStudentList();
        initView();

    }

    public void initStudentList(){

        studentArrayList=new ArrayList<>();

        for (int i = 0; i <40 ; i++) {

            studentArrayList.add(new Student(

                    1+i,
                    "Student"

            ));
        }

    }

    public void initView(){

        recyclerView = findViewById(R.id.recylerViewForProducts);
        studentAdapter=new StudentAdapter(studentArrayList);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Main_activity_xml1.this,

                LinearLayoutManager.VERTICAL,
                false

                ));

    }

}
