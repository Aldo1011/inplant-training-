package com.example.exit_machinetest;

import java.util.List;

public class DocBooks {


    List<String> author_name;
    int cover_i;
    List<String> first_publish_year;
    int cover_edition_key;
    String title;


    public List<String> getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(List<String> author_name) {
        this.author_name = author_name;
    }

    public int getCover_i() {
        return cover_i;
    }

    public void setCover_i(int cover_i) {
        this.cover_i = cover_i;
    }

    public List<String> getFirst_publish_year() {
        return first_publish_year;
    }

    public void setFirst_publish_year(List<String> first_publish_year) {
        this.first_publish_year = first_publish_year;
    }

    public int getCover_edition_key() {
        return cover_edition_key;
    }

    public void setCover_edition_key(int cover_edition_key) {
        this.cover_edition_key = cover_edition_key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DocBooks(int cover_i, List<String> author_name, List<String> first_publish_year, int cover_edition_key, String title) {
        this.cover_i = cover_i;
        this.author_name = author_name;
        this.first_publish_year = first_publish_year;
        this.cover_edition_key = cover_edition_key;
        this.title = title;
    }




}
