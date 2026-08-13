package com.example.internshipproject_codestreak.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.internshipproject_codestreak.R;
import com.example.internshipproject_codestreak.data.LessonCatalog;
import com.example.internshipproject_codestreak.models.User;
import com.example.internshipproject_codestreak.repository.UserRepository;
import com.example.internshipproject_codestreak.viewmodel.CodeStreakMapView;
import com.example.internshipproject_codestreak.viewmodel.Lesson;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private CodeStreakMapView codeStreakMap;
    private UserRepository userRepository;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.homepage_activity);

        codeStreakMap = findViewById(
                R.id.codeStreakMap
        );

        userRepository = new UserRepository();

        checkDailyHearts();
    }

    private void checkDailyHearts() {

        FirebaseAuth auth =
                FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {

            Toast.makeText(
                    this,
                    "User not logged in.",
                    Toast.LENGTH_LONG
            ).show();

            return;
        }

        String uid =
                auth.getCurrentUser().getUid();

        userRepository
                .resetHeartsIfNeeded(uid)
                .addOnSuccessListener(user -> {

                    if (user == null) {

                        Toast.makeText(
                                this,
                                "Could not load user data.",
                                Toast.LENGTH_LONG
                        ).show();

                        return;
                    }

                    currentUser = user;

                    setupLessons();

                })
                .addOnFailureListener(e -> {

                    Toast.makeText(
                            this,
                            "Could not load your progress.",
                            Toast.LENGTH_LONG
                    ).show();
                });
    }

    private void setupLessons() {

        List<Lesson> lessons =
                LessonCatalog.getLessons();

        List<Integer> completedLessons =
                currentUser.getCompletedLessons();

        if (completedLessons == null) {
            completedLessons = new java.util.ArrayList<>();
        }

        for (int i = 0; i < lessons.size(); i++) {

            Lesson lesson = lessons.get(i);

            // ---------------------------------------
            // LESSON 1 IS ALWAYS UNLOCKED
            // ---------------------------------------

            if (i == 0) {

                lesson.setUnlocked(true);

                continue;
            }

            // ---------------------------------------
            // PREVIOUS LESSON
            // ---------------------------------------

            Lesson previousLesson =
                    lessons.get(i - 1);

            // ---------------------------------------
            // UNLOCK IF PREVIOUS IS COMPLETED
            // ---------------------------------------

            if (completedLessons.contains(
                    previousLesson.getId()
            )) {

                lesson.setUnlocked(true);

            } else {

                lesson.setUnlocked(false);
            }
        }

        // ---------------------------------------
        // SEND TO MAP
        // ---------------------------------------

        codeStreakMap.setLessons(
                lessons,
                lesson -> {

                    // ---------------------------------------
                    // CHECK HEARTS
                    // ---------------------------------------

                    if (currentUser.getHearts() <= 0) {

                        Toast.makeText(
                                this,
                                "You're out of hearts. Come back tomorrow!",
                                Toast.LENGTH_LONG
                        ).show();

                        return;
                    }

                    // ---------------------------------------
                    // OPEN LESSON
                    // ---------------------------------------

                    Intent intent = new Intent(
                            HomeActivity.this,
                            LessonActivity.class
                    );

                    intent.putExtra(
                            "LESSON_ID",
                            lesson.getId()
                    );

                    startActivity(intent);
                }
        );
    }

}