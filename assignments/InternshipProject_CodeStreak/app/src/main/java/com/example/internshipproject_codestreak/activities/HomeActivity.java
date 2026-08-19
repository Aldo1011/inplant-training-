package com.example.internshipproject_codestreak.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
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

    private TextView streakValue;
    private TextView heartsValue;
    private TextView xpValue;
    private TextView worldLabel;
    private TextView worldTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.homepage_activity);

        codeStreakMap = findViewById(
                R.id.codeStreakMap
        );

        streakValue = findViewById(R.id.streakValue);
        heartsValue = findViewById(R.id.heartsValue);
        xpValue = findViewById(R.id.xpValue);

        worldLabel = findViewById(R.id.worldLabel);
        worldTitle = findViewById(R.id.worldTitle);

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

                    updatePlayerStats();

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

    private void updatePlayerStats() {

        streakValue.setText(
                "🔥 " + currentUser.getStreak()
        );

        heartsValue.setText(
                "❤️ " + currentUser.getHearts()
        );

        xpValue.setText(
                "⭐ " + currentUser.getXp()
        );

        worldLabel.setText(
                "WORLD " + currentUser.getCurrentWorld()
        );

        worldTitle.setText(
                getWorldTitle(
                        currentUser.getCurrentWorld()
                )
        );
    }

    private String getWorldTitle(int world) {

        switch (world) {

            case 1:
                return "THE AWAKENING";

            case 2:
                return "THE DECISION PATH";

            default:
                return "UNKNOWN WORLD";
        }
    }

}