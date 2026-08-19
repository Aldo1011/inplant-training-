package com.example.internshipproject_codestreak.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.internshipproject_codestreak.R;
import com.example.internshipproject_codestreak.models.User;
import com.example.internshipproject_codestreak.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {


    private TextView profileUsername;
    private TextView profileEmail;
    private TextView profileXp;
    private TextView profileStreak;
    private TextView profileLessons;
    private TextView profileWorld;

    private UserRepository userRepository;

    @Override
    protected void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.profile_activity
        );

        initViews();

        loadProfile();
    }

    private void initViews() {

        profileUsername =
                findViewById(
                        R.id.profileUsername
                );

        profileEmail =
                findViewById(
                        R.id.profileEmail
                );

        profileXp =
                findViewById(
                        R.id.profileXp
                );

        profileStreak =
                findViewById(
                        R.id.profileStreak
                );

        profileLessons =
                findViewById(
                        R.id.profileLessons
                );

        profileWorld =
                findViewById(
                        R.id.profileWorld
                );

        userRepository =
                new UserRepository();
    }

    private void loadProfile() {

        FirebaseAuth auth =
                FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            return;
        }

        String uid =
                auth.getCurrentUser().getUid();

        userRepository
                .getUser(uid)
                .addOnSuccessListener(
                        this::displayUser
                )
                .addOnFailureListener(
                        e -> Toast.makeText(
                                this,
                                "Could not load profile.",
                                Toast.LENGTH_SHORT
                        ).show()
                );
    }

    private void displayUser(
            User user
    ) {

        if (user == null) {
            return;
        }

        profileUsername.setText(
                user.getUsername()
        );

        profileEmail.setText(
                user.getEmail()
        );

        profileXp.setText(
                "⭐ " + user.getXp()
        );

        profileStreak.setText(
                "🔥 " + user.getStreak()
        );

        int completed =
                user.getCompletedLessons() == null
                        ? 0
                        : user.getCompletedLessons().size();

        profileLessons.setText(
                String.valueOf(completed)
        );

        profileWorld.setText(
                getWorldTitle(
                        user.getCurrentWorld()
                )
        );
    }

    private String getWorldTitle(
            int world
    ) {

        switch (world) {

            case 1:
                return "The Awakening";

            case 2:
                return "The Decision Path";

            default:
                return "Unknown World";
        }
    }
}
