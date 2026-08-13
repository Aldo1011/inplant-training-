package com.example.internshipproject_codestreak.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String email;
    private int xp;
    private int level;
    private int streak;
    private int currentWorld;
    private int currentLesson;
    private int hearts;

    private List<Integer> completedLessons;

    public String getLastHeartReset() {
        return lastHeartReset;
    }

    public void setLastHeartReset(String lastHeartReset) {
        this.lastHeartReset = lastHeartReset;
    }

    private String lastHeartReset;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getCurrentWorld() {
        return currentWorld;
    }

    public void setCurrentWorld(int currentWorld) {
        this.currentWorld = currentWorld;
    }

    public int getCurrentLesson() {
        return currentLesson;
    }

    public void setCurrentLesson(int currentLesson) {
        this.currentLesson = currentLesson;
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public List<Integer> getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(List<Integer> completedLessons) {
        this.completedLessons = completedLessons;
    }

    public boolean hasCompletedLesson(int lessonId) {

        return completedLessons != null
                && completedLessons.contains(lessonId);
    }

    public void completeLesson(int lessonId) {

        if (completedLessons == null) {
            completedLessons = new ArrayList<>();
        }

        if (!completedLessons.contains(lessonId)) {
            completedLessons.add(lessonId);
        }
    }

    public User() {
        // Required for Firebase

        xp = 0;
        level = 1;
        streak = 0;
        currentWorld = 1;
        currentLesson = 1;
        hearts = 5;
        lastHeartReset = null;
        completedLessons = new ArrayList<>();

    }


}
