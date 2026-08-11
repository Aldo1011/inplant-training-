package com.example.exit_machinetest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    ArrayList<DocBooks> docBooks;

    public BookAdapter(ArrayList<DocBooks> docBooksArrayList){

    this.docBooks=docBooksArrayList;

    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        ImageView bookCover;
        TextView titleTextView,


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }







}
