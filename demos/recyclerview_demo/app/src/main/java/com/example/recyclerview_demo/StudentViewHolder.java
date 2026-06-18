package com.example.recyclerview_demo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {

   TextView studentNAme;
    TextView studentId;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);

        studentId=itemView.findViewById(R.id.studentid);
        studentNAme=itemView.findViewById(R.id.studentName);

    }

}
