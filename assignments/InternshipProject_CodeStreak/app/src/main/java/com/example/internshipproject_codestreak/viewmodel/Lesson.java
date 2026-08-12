package com.example.internshipproject_codestreak.viewmodel;

import java.util.List;

public class Lesson {

    private int id;
    private String title;
    private String description;
    private String content;
    private String exampleCode;

    private int xpReward;

    private boolean unlocked;
    private boolean completed;



    private List<Challenge> challenges;

    public Lesson() {
        // Required for Firebase
    }

    public Lesson(
            int id,
            String title,
            String description,
            String content,
            String exampleCode,
            int xpReward,
            boolean unlocked,
            boolean completed,
            List<Challenge> challenges
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.exampleCode = exampleCode;
        this.xpReward = xpReward;
        this.unlocked = unlocked;
        this.completed = completed;
        this.challenges = challenges;
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

    public String getContent() {
        return content;
    }

    public String getExampleCode() {
        return exampleCode;
    }

    public int getXpReward() {
        return xpReward;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public boolean isCompleted() {
        return completed;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
