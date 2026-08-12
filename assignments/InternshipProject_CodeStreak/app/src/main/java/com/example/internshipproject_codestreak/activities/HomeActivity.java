package com.example.internshipproject_codestreak.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.internshipproject_codestreak.R;
import com.example.internshipproject_codestreak.data.LessonCatalog;
import com.example.internshipproject_codestreak.viewmodel.CodeStreakMapView;
import com.example.internshipproject_codestreak.viewmodel.Lesson;

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

        List<Lesson> lessons = LessonCatalog.getLessons();

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