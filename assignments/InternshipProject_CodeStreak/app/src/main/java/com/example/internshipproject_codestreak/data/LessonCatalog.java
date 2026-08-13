package com.example.internshipproject_codestreak.data;

import com.example.internshipproject_codestreak.viewmodel.Challenge;
import com.example.internshipproject_codestreak.viewmodel.Lesson;
import com.example.internshipproject_codestreak.viewmodel.TestCase;

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
                        "The print() function displays text on the screen.",
                        null

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
                        "print() displays the text written inside the parentheses.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_FIX,
                        "Fix the code so that it runs correctly.",
                        "print(\"SYSTEM ONLINE\"",
                        null,
                        "print(\"SYSTEM ONLINE\")",
                        "The closing parenthesis is required to complete the print() function.",
                        null
                ),

                new Challenge(
                        4,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Make the terminal display both lines.",
                        "",
                        null,
                        "CODESTREAK\nSYSTEM ONLINE",
                        "You can use multiple print() statements to display multiple lines.",
                        null

                )
        );

        List<Challenge> lesson8Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_EXECUTION,

                        "The terminal is ready for its first real test.\n\n"
                                + "Create a character initialization program.\n\n"
                                + "Your program must:\n"
                                + "1. Ask the player for their name.\n"
                                + "2. Ask for their age.\n"
                                + "3. Ask for their starting health.\n"
                                + "4. Convert the age into a number.\n"
                                + "5. Display the character information.\n\n"
                                + "Your output must follow this format:\n\n"
                                + "PLAYER INITIALIZED\n"
                                + "Name: <name>\n"
                                + "Age: <age>\n"
                                + "Health: <health>",

                        "Example interaction:\n\n"
                                + "Name: Nova\n"
                                + "Age: 18\n"
                                + "Health: 100",

                        null,

                        null,

                        "Use input() to collect information and int() "
                                + "to convert the age.",

                        Arrays.asList(

                                new TestCase(
                                        "Nova\n18\n100",
                                        "PLAYER INITIALIZED\n"
                                                + "Name: Nova\n"
                                                + "Age: 18\n"
                                                + "Health: 100"
                                ),

                                new TestCase(
                                        "Astra\n21\n80",
                                        "PLAYER INITIALIZED\n"
                                                + "Name: Astra\n"
                                                + "Age: 21\n"
                                                + "Health: 80"
                                ),

                                new TestCase(
                                        "Rex\n16\n120",
                                        "PLAYER INITIALIZED\n"
                                                + "Name: Rex\n"
                                                + "Age: 16\n"
                                                + "Health: 120"
                                )
                        )
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

        Lesson lesson8 = new Lesson(
                8,
                "The First Trial",

                "Build your first complete Python program.",

                "The terminal goes silent.\n\n"
                        + "You have learned how to speak to it, "
                        + "store information, work with different "
                        + "types, receive input, and manipulate data.\n\n"
                        + "Now the terminal gives you one final task.\n\n"
                        + "Build a character initialization system.\n\n"
                        + "This time, there will be no step-by-step instructions "
                        + "and no multiple-choice answers.\n\n"
                        + "You write the program.",

                "name = input()\n"
                        + "age = int(input())\n"
                        + "health = int(input())\n\n"
                        + "print(\"PLAYER INITIALIZED\")\n"
                        + "print(f\"Name: {name}\")\n"
                        + "print(f\"Age: {age}\")\n"
                        + "print(f\"Health: {health}\")",

                200,

                false,
                false,

                lesson8Challenges
        );

        lessons.add(lesson1);
        lessons.add(lesson8);

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
