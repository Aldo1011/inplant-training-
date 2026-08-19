package com.example.internshipproject_codestreak.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

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
    private TextView profileButton;
    private LinearLayout navHome;
    private LinearLayout navStore;
    private LinearLayout navProfile;

    private LinearLayout streakCard;
    private LinearLayout heartsCard;
    private LinearLayout xpCard;

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

        navHome = findViewById(R.id.navHome);
        navStore = findViewById(R.id.navStore);
        navProfile = findViewById(R.id.navProfile);

        streakCard = findViewById(R.id.streakCard);
        heartsCard = findViewById(R.id.heartsCard);
        xpCard = findViewById(R.id.xpCard);

        userRepository = new UserRepository();

        checkDailyHearts();

        profileButton =
                findViewById(R.id.profileButton);

        profileButton.setOnClickListener(
                v -> showProfileMenu()
        );

        navHome.setOnClickListener(
                v -> {
                    // Already on Home
                }
        );

        navStore.setOnClickListener(
                v -> {

                    startActivity(
                            new Intent(
                                    HomeActivity.this,
                                    StoreActivity.class
                            )
                    );
                }
        );

        navProfile.setOnClickListener(
                v -> {

                    startActivity(
                            new Intent(
                                    HomeActivity.this,
                                    ProfileActivity.class
                            )
                    );
                }
        );

        streakCard.setOnClickListener(
                v -> wiggleCard(streakCard)
        );

        heartsCard.setOnClickListener(
                v -> wiggleCard(heartsCard)
        );

        xpCard.setOnClickListener(
                v -> wiggleCard(xpCard)
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            checkDailyHearts();
        }

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


    private void showProfileMenu() {

        PopupMenu popupMenu =
                new PopupMenu(
                        HomeActivity.this,
                        profileButton
                );

        popupMenu.getMenu().add(
                "Profile"
        );

        popupMenu.getMenu().add(
                "Logout"
        );

        popupMenu.setOnMenuItemClickListener(
                item -> {

                    String title =
                            item.getTitle().toString();

                    if (title.equals("Profile")) {

                        startActivity(
                                new Intent(
                                        HomeActivity.this,
                                        ProfileActivity.class
                                )
                        );

                        return true;
                    }

                    if (title.equals("Logout")) {

                        logoutUser();

                        return true;
                    }

                    return false;
                }
        );

        popupMenu.show();
    }

    private void logoutUser() {

        FirebaseAuth.getInstance()
                .signOut();

        Intent intent =
                new Intent(
                        HomeActivity.this,
                        LoginActivity.class
                );

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
        );

        startActivity(intent);

        finish();
    }

    private void wiggleCard(View view) {

        view.animate()
                .rotation(-7f)
                .scaleX(1.04f)
                .scaleY(1.04f)
                .setDuration(75)
                .withEndAction(() ->

                        view.animate()
                                .rotation(7f)
                                .setDuration(75)
                                .withEndAction(() ->

                                        view.animate()
                                                .rotation(0f)
                                                .scaleX(1f)
                                                .scaleY(1f)
                                                .setDuration(75)
                                                .start()

                                )
                                .start()

                )
                .start();
    }

}