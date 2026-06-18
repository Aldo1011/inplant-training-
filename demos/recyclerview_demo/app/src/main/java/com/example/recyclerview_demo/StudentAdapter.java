package com.example.recyclerview_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {


    ArrayList<Student> studentArrayList;

    public StudentAdapter(ArrayList<Student> arrayList){


        this.studentArrayList=arrayList;

    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(

                R.layout.student_item,
                parent,
                false

        );

        return new StudentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        Student student= studentArrayList.get(position);

        holder.studentNAme.setText(student.getStudentName());
        holder.studentId.setText(String.format("%d", student.getStudentId()));


    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }
}
