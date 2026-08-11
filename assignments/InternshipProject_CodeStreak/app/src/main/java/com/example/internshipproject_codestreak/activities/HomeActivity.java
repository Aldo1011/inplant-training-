package com.example.internshipproject_codestreak.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.internshipproject_codestreak.R;
import com.example.internshipproject_codestreak.models.Lesson;
import com.example.internshipproject_codestreak.viewmodel.CodeStreakMapView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private CodeStreakMapView codeStreakMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.homepage_activity);

        // ---------------------------------------
        // CODESTREAK MAP
        // ---------------------------------------

        codeStreakMap = findViewById(
                R.id.codeStreakMap
        );

        // ---------------------------------------
        // LESSONS
        // ---------------------------------------

        List<Lesson> lessons = new ArrayList<>();

        lessons.add(new Lesson(
                1,
                "Hello Python",
                "Your first Python program",
                true,
                true
        ));

        lessons.add(new Lesson(
                2,
                "Variables",
                "Learn how Python stores information",
                true,
                false
        ));

        lessons.add(new Lesson(
                3,
                "Data Types",
                "Numbers, strings and booleans",
                false,
                false
        ));

        lessons.add(new Lesson(
                4,
                "Conditions",
                "Make your programs think",
                false,
                false
        ));

        lessons.add(new Lesson(
                5,
                "Loops",
                "Make Python repeat itself",
                false,
                false
        ));

        // ---------------------------------------
        // SEND LESSONS TO THE MAP
        // ---------------------------------------

        codeStreakMap.setLessons(
                lessons,
                lesson -> {

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