package com.example.internshipproject_codestreak;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.internshipproject_codestreak.activities.HomeActivity;
import com.example.internshipproject_codestreak.activities.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 1200;
    private static final int HOLD_DURATION = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showSplashAnimation();
    }

    private void showSplashAnimation() {

        // -------------------------------------------------
        // ROOT
        // -------------------------------------------------

        FrameLayout root =
                new FrameLayout(this);

        root.setBackgroundColor(
                getResources().getColor(
                        R.color.cs_secondary
                )
        );


        // -------------------------------------------------
        // LOGO ROW
        // -------------------------------------------------

        LinearLayout logoRow =
                new LinearLayout(this);

        logoRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        logoRow.setGravity(
                Gravity.CENTER_VERTICAL
        );

        FrameLayout.LayoutParams rowParams =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                );

        rowParams.gravity =
                Gravity.CENTER;

        root.addView(
                logoRow,
                rowParams
        );


        // -------------------------------------------------
        // C
        // -------------------------------------------------

        TextView cText =
                new TextView(this);

        cText.setText("C");

        cText.setTextSize(52);

        cText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        cText.setTextColor(
                getResources().getColor(
                        R.color.cs_primary
                )
        );

        cText.setGravity(
                Gravity.CENTER
        );


        // -------------------------------------------------
        // ODESTREAK
        // -------------------------------------------------

        TextView restText =
                new TextView(this);

        restText.setText("odeStreak");

        restText.setTextSize(52);

        restText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        restText.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        restText.setGravity(
                Gravity.CENTER_VERTICAL
        );

        // Start hidden and collapsed.
        restText.setAlpha(0f);
        restText.setScaleX(0f);

        // Important:
        // Scale from its left edge so the letters
        // appear to grow outward from the C.
        restText.setPivotX(0f);
        restText.setPivotY(0.5f);


        logoRow.addView(cText);

        logoRow.addView(restText);


        // -------------------------------------------------
        // SHOW SCREEN
        // -------------------------------------------------

        setContentView(root);


        // -------------------------------------------------
        // WAIT FOR LAYOUT
        // -------------------------------------------------

        logoRow.post(() -> {

            float movement =
                    restText.getWidth() / 2f;

            // C begins visually in the center.
            cText.setTranslationX(
                    movement
            );


            // -------------------------------------------------
            // C MOVES LEFT
            // -------------------------------------------------

            ObjectAnimator cMove =
                    ObjectAnimator.ofFloat(
                            cText,
                            "translationX",
                            movement,
                            0f
                    );

            cMove.setDuration(650);


            // -------------------------------------------------
            // ODESTREAK APPEARS
            // -------------------------------------------------

            ObjectAnimator revealAlpha =
                    ObjectAnimator.ofFloat(
                            restText,
                            "alpha",
                            0f,
                            1f
                    );

            revealAlpha.setDuration(500);


            ObjectAnimator revealScale =
                    ObjectAnimator.ofFloat(
                            restText,
                            "scaleX",
                            0f,
                            1f
                    );

            revealScale.setDuration(650);


            // -------------------------------------------------
            // COMBINE
            // -------------------------------------------------

            AnimatorSet animation =
                    new AnimatorSet();

            animation.playTogether(
                    cMove,
                    revealAlpha,
                    revealScale
            );

            animation.start();


            // -------------------------------------------------
            // NAVIGATE AFTER ANIMATION
            // -------------------------------------------------

            new Handler().postDelayed(
                    this::openNextScreen,
                    SPLASH_DURATION + HOLD_DURATION
            );

        });
    }


    private void openNextScreen() {

        FirebaseUser user =
                FirebaseAuth
                        .getInstance()
                        .getCurrentUser();

        Intent intent;

        if (user != null) {

            intent =
                    new Intent(
                            MainActivity.this,
                            HomeActivity.class
                    );

        } else {

            intent =
                    new Intent(
                            MainActivity.this,
                            LoginActivity.class
                    );
        }

        startActivity(intent);

        finish();
    }
}