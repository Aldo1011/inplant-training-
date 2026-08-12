package com.example.internshipproject_codestreak.data;

import com.example.internshipproject_codestreak.viewmodel.Challenge;
import com.example.internshipproject_codestreak.viewmodel.Lesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LessonCatalog {

    public static List<Lesson> getLessons() {

        List<Lesson> lessons = new ArrayList<>();

        // ---------------------------------------
        // LESSON 1 — AWAKENING
        // ---------------------------------------

        List<Challenge> lesson1Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Make the terminal display SYSTEM ONLINE.",
                        "print(\"___________\")",
                        null,
                        "SYSTEM ONLINE",
                        "The print() function displays text on the screen."
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What will this program display?",
                        "print(\"Hello, Python!\")",
                                 Arrays.asList(
                                "Hello, Python!",
                                "\"Hello, Python!\"",
                                "print(Hello, Python!)",
                                "Nothing"),
                        "Hello, Python!",
                        "print() displays the text written inside the parentheses."
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_FIX,
                        "Fix the code so that it runs correctly.",
                        "print(\"SYSTEM ONLINE\"",
                        null,
                        "print(\"SYSTEM ONLINE\")",
                        "The closing parenthesis is required to complete the print() function."
                ),

                new Challenge(
                        4,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Make the terminal display both lines.",
                        "",
                        null,
                        "CODESTREAK\nSYSTEM ONLINE",
                        "You can use multiple print() statements to display multiple lines."
                )
        );

        Lesson lesson1 = new Lesson(
                1,
                "Awakening",
                "Write your first Python program.",
                "The terminal flickers to life.\n\n"
                        + "A single message appears:\n\n"
                        + "SYSTEM OFFLINE\n\n"
                        + "You need to teach the terminal its first command.\n\n"
                        + "Python is a programming language used to tell a computer what to do.\n\n"
                        + "One of the simplest Python commands is print(). "
                        + "It displays something on the screen.",
                "print(\"Hello, Python!\")",
                100,
                true,
                false,
                lesson1Challenges
        );

        lessons.add(lesson1);

        return lessons;
    }

    public static Lesson getLessonById(int id) {

        List<Lesson> lessons = getLessons();

        for (Lesson lesson : lessons) {

            if (lesson.getId() == id) {
                return lesson;
            }
        }

        return null;
    }


}
