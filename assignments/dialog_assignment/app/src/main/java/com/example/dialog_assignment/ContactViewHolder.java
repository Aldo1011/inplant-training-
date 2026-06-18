package com.example.dialog_assignment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    TextView contactName;
    TextView contactNumber;


    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);

        contactNumber=itemView.findViewById(R.id.contactNumberTextView);
        contactName=itemView.findViewById(R.id.contactNameTextView);

    }

}
