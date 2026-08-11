package com.example.internshipproject_codestreak.models;

public class Lesson {

    private final int id;
    private final String title;
    private final String description;
    private final boolean unlocked;
    private final boolean completed;

    public Lesson(
            int id,
            String title,
            String description,
            boolean unlocked,
            boolean completed
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.unlocked = unlocked;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public boolean isCompleted() {
        return completed;
    }
}