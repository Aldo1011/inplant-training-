package com.example.internshipproject_codestreak.data;

import com.example.internshipproject_codestreak.viewmodel.Challenge;
import com.example.internshipproject_codestreak.viewmodel.Lesson;
import com.example.internshipproject_codestreak.viewmodel.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LessonCatalog {

    // =====================================================
    // PUBLIC LESSON CATALOG
    // =====================================================

    public static List<Lesson> getLessons() {

        List<Lesson> lessons = new ArrayList<>();

        // =================================================
        // WORLD 1 — THE AWAKENING
        // =================================================

        // -------------------------------------------------
        // LESSON 1 — AWAKENING
        // -------------------------------------------------

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
                                "Nothing"
                        ),
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

        lessons.add(
                new Lesson(
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
                )
        );

        // -------------------------------------------------
        // LESSON 2 — THE MEMORY
        // -------------------------------------------------

        List<Challenge> lesson2Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Create a variable named score and store 100 in it.",
                        "",
                        null,
                        "score = 100",
                        "The = operator assigns a value to a variable.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What will this program print?",
                        "score = 50\nscore = 100\nprint(score)",
                        Arrays.asList(
                                "50",
                                "100",
                                "50 100",
                                "Nothing"
                        ),
                        "100",
                        "A variable can be reassigned. The final value stored in score is 100.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_FIX,
                        "Fix the variable name.",
                        "player name = \"Nova\"",
                        null,
                        "player_name = \"Nova\"",
                        "Variable names cannot contain spaces. Use an underscore instead.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        2,
                        "The Memory",
                        "Teach Python how to remember information.",
                        "The terminal can speak, but it cannot remember.\n\n"
                                + "You discover memory slots called variables.\n\n"
                                + "A variable gives a name to a piece of information.\n\n"
                                + "For example:\n\n"
                                + "name = \"Nova\"\n"
                                + "score = 100",
                        "name = \"Nova\"\nscore = 100\nprint(name)\nprint(score)",
                        100,
                        false,
                        false,
                        lesson2Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 3 — THE FOUR SIGNALS
        // -------------------------------------------------

        List<Challenge> lesson3Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What type of value is 42?",
                        "value = 42",
                        Arrays.asList(
                                "str",
                                "int",
                                "float",
                                "bool"
                        ),
                        "int",
                        "42 is a whole number, so Python stores it as an int.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What does this print?",
                        "print(type(\"Hello\"))",
                        Arrays.asList(
                                "<class 'str'>",
                                "<class 'int'>",
                                "<class 'float'>",
                                "<class 'bool'>"
                        ),
                        "<class 'str'>",
                        "Text surrounded by quotes is a string.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Create four variables: a string, an integer, a float, and a boolean.",
                        "",
                        null,
                        "name = \"Nova\"\nlevel = 3\nhealth = 97.5\nalive = True",
                        "Python has different basic data types for different kinds of values.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        3,
                        "The Four Signals",
                        "Learn how Python stores different kinds of information.",
                        "Not all information is the same.\n\n"
                                + "Python has several basic data types:\n\n"
                                + "str  → text\n"
                                + "int  → whole numbers\n"
                                + "float → decimal numbers\n"
                                + "bool → True or False\n\n"
                                + "You can inspect a value using type().",
                        "name = \"Nova\"\nlevel = 3\nhealth = 97.5\nalive = True\n\nprint(type(name))",
                        100,
                        false,
                        false,
                        lesson3Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 4 — THE MESSENGER
        // -------------------------------------------------

        List<Challenge> lesson4Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Ask the user for their name and store it in a variable called name.",
                        "",
                        null,
                        "name = input()",
                        "input() lets the program receive information from the user.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What type does input() return?",
                        "age = input()",
                        Arrays.asList(
                                "int",
                                "float",
                                "str",
                                "bool"
                        ),
                        "str",
                        "input() returns text. You can convert it later if you need a number.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Create a program which asks for a name and prints Hello followed by the name.",
                        "",
                        null,
                        "name = input()\nprint(\"Hello \" + name)",
                        "Store the input first, then use it when printing the greeting.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        4,
                        "The Messenger",
                        "Teach Python to listen to the user.",
                        "The terminal responds to commands.\n\n"
                                + "But now it asks a different question:\n\n"
                                + "Can you hear me?\n\n"
                                + "The input() function allows your program to receive information.",
                        "name = input()\nprint(\"Hello \" + name)",
                        100,
                        false,
                        false,
                        lesson4Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 5 — THE OPERATORS
        // -------------------------------------------------

        List<Challenge> lesson5Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What is the result of 17 % 5?",
                        "print(17 % 5)",
                        Arrays.asList(
                                "2",
                                "3",
                                "5",
                                "12"
                        ),
                        "2",
                        "The % operator gives the remainder after division.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What does this comparison produce?",
                        "print(10 > 3)",
                        Arrays.asList(
                                "10",
                                "3",
                                "True",
                                "False"
                        ),
                        "True",
                        "10 is greater than 3, so the comparison is True.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Health starts at 80. Damage is 25. Calculate and print the remaining health.",
                        "",
                        null,
                        "health = 80\ndamage = 25\nhealth = health - damage\nprint(health)",
                        "Arithmetic operators let your program manipulate numbers.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        5,
                        "The Operators",
                        "Learn how Python calculates and compares values.",
                        "The terminal unlocks its logic system.\n\n"
                                + "You can add, subtract, multiply and divide.\n\n"
                                + "You can also compare values and combine conditions.\n\n"
                                + "These operations will become the foundation for making decisions.",
                        "health = 80\ndamage = 25\nhealth = health - damage\nprint(health)",
                        125,
                        false,
                        false,
                        lesson5Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 6 — CHANGING FORM
        // -------------------------------------------------

        List<Challenge> lesson6Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Convert the text \"42\" into an integer.",
                        "",
                        null,
                        "int(\"42\")",
                        "int() converts suitable text into an integer.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What happens here?",
                        "\"10\" + \"5\"",
                        Arrays.asList(
                                "15",
                                "105",
                                "10 5",
                                "Error"
                        ),
                        "105",
                        "Both values are strings, so + joins them together.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_FIX,
                        "Make this program add two numbers correctly.",
                        "a = input()\nb = input()\nprint(a + b)",
                        null,
                        "a = int(input())\nb = int(input())\nprint(a + b)",
                        "Input starts as text, so convert it to integers before arithmetic.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        6,
                        "Changing Form",
                        "Learn how to convert values between types.",
                        "The terminal reveals another ability.\n\n"
                                + "Information can change form.\n\n"
                                + "For example, \"100\" is text while 100 is a number.\n\n"
                                + "Python provides int(), float(), str(), and bool() for common conversions.",
                        "age = int(input())\nprint(age + 1)",
                        125,
                        false,
                        false,
                        lesson6Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 7 — THE VOICE OF PYTHON
        // -------------------------------------------------

        List<Challenge> lesson7Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Create the string \"Hello World\" using quotes.",
                        "",
                        null,
                        "\"Hello World\"",
                        "Python strings can be written using single or double quotes.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What will this print?",
                        "name = \"Nova\"\nlevel = 5\nprint(f\"{name} - Level {level}\")",
                        Arrays.asList(
                                "Nova - Level 5",
                                "{name} - Level {level}",
                                "Nova Level",
                                "Error"
                        ),
                        "Nova - Level 5",
                        "An f-string allows variables to be inserted directly into text.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Print the two words on separate lines.",
                        "",
                        null,
                        "print(\"CODESTREAK\")\nprint(\"ONLINE\")",
                        "\\n creates a new line, and multiple print() calls also create separate lines.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        7,
                        "The Voice of Python",
                        "Learn how Python works with text.",
                        "The terminal's messages become more powerful.\n\n"
                                + "You can combine strings, format them using f-strings, "
                                + "and control their layout with escape sequences.\n\n"
                                + "Now Python can speak more naturally.",
                        "name = \"Nova\"\nlevel = 5\nprint(f\"Welcome {name}. Level: {level}\")",
                        125,
                        false,
                        false,
                        lesson7Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 8 — THE FIRST TRIAL (BOSS)
        // -------------------------------------------------

        List<Challenge> lesson8Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_EXECUTION,
                        "The terminal is ready for its first real test.\n\n"
                                + "Create a character initialization program.\n\n"
                                + "Your program must:\n"
                                + "1. Ask for the player's name.\n"
                                + "2. Ask for the player's age.\n"
                                + "3. Ask for starting health.\n"
                                + "4. Convert age into a number.\n"
                                + "5. Display the character information.\n\n"
                                + "Output format:\n\n"
                                + "PLAYER INITIALIZED\n"
                                + "Name: <name>\n"
                                + "Age: <age>\n"
                                + "Health: <health>",

                        "",

                        null,
                        null,

                        "Use input(), variables, int(), and print().",

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

        lessons.add(
                new Lesson(
                        8,
                        "The First Trial",
                        "Build your first complete Python program.",
                        "The terminal goes silent.\n\n"
                                + "You have learned how to speak to it, "
                                + "store information, work with different types, "
                                + "receive input, manipulate numbers, and format text.\n\n"
                                + "Now there will be no step-by-step solution.\n\n"
                                + "Build the program yourself.",
                        "",
                        200,
                        false,
                        false,
                        lesson8Challenges
                )
        );

        // =================================================
        // WORLD 2 — THE DECISION PATH
        // =================================================

        // -------------------------------------------------
        // LESSON 9 — THE FORK
        // -------------------------------------------------

        List<Challenge> lesson9Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Print ALIVE only when health is greater than 0.",
                        "",
                        null,
                        "if health > 0:\n    print(\"ALIVE\")",
                        "if lets the program execute code only when a condition is true.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "Will the message print?",
                        "health = 0\nif health > 0:\n    print(\"ALIVE\")",
                        Arrays.asList(
                                "Yes",
                                "No",
                                "Only sometimes",
                                "Python crashes"
                        ),
                        "No",
                        "0 is not greater than 0, so the condition is false.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_FIX,
                        "Fix the indentation.",
                        "health = 50\nif health > 0:\nprint(\"ALIVE\")",
                        null,
                        "health = 50\nif health > 0:\n    print(\"ALIVE\")",
                        "Python uses indentation to define the body of a block.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        9,
                        "The Fork",
                        "Teach Python to make its first decision.",
                        "The world changes.\n\n"
                                + "The terminal no longer follows every instruction blindly.\n\n"
                                + "It can now choose whether an instruction should run.\n\n"
                                + "Your first decision begins with if.",
                        "health = 50\n\nif health > 0:\n    print(\"ALIVE\")",
                        125,
                        false,
                        false,
                        lesson9Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 10 — THE CHOICE
        // -------------------------------------------------

        List<Challenge> lesson10Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "Which branch runs when score = 80?",
                        "if score >= 90:\n    print(\"A\")\nelif score >= 50:\n    print(\"B\")\nelse:\n    print(\"C\")",
                        Arrays.asList(
                                "A",
                                "B",
                                "C",
                                "Nothing"
                        ),
                        "B",
                        "80 is below 90 but at least 50, so the elif branch runs.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Print HIGH when health is above 75, otherwise print LOW.",
                        "",
                        null,
                        "if health > 75:\n    print(\"HIGH\")\nelse:\n    print(\"LOW\")",
                        "else handles the case where the if condition is false.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_FIX,
                        "Fix the conditional structure.",
                        "score = 30\nif score > 80:\n    print(\"A\")\nelse if score > 50:\n    print(\"B\")",
                        null,
                        "score = 30\nif score > 80:\n    print(\"A\")\nelif score > 50:\n    print(\"B\")",
                        "Python uses elif, not else if.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        10,
                        "The Choice",
                        "Learn how Python chooses between several paths.",
                        "One decision is not enough.\n\n"
                                + "Programs often need several possible outcomes.\n\n"
                                + "Python provides if, elif, and else to handle these branches.",
                        "score = 75\n\n"
                                + "if score >= 90:\n"
                                + "    print(\"A\")\n"
                                + "elif score >= 50:\n"
                                + "    print(\"B\")\n"
                                + "else:\n"
                                + "    print(\"C\")",
                        125,
                        false,
                        false,
                        lesson10Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 11 — THE LOGIC CORE
        // -------------------------------------------------

        List<Challenge> lesson11Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What does this produce?",
                        "health = 80\nenergy = 50\nprint(health > 0 and energy > 20)",
                        Arrays.asList(
                                "True",
                                "False",
                                "80",
                                "50"
                        ),
                        "True",
                        "Both conditions are true, so and produces True.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What does not False produce?",
                        "print(not False)",
                        Arrays.asList(
                                "True",
                                "False",
                                "None",
                                "Error"
                        ),
                        "True",
                        "not reverses a boolean value.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Open the gate only when the player is level 5 or higher AND has a key.",
                        "",
                        null,
                        "if level >= 5 and has_key:\n    print(\"Gate opened\")",
                        "and requires both conditions to be true.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        11,
                        "The Logic Core",
                        "Combine multiple conditions.",
                        "The terminal's decision system grows stronger.\n\n"
                                + "Python can combine conditions using and, or, and not.\n\n"
                                + "Now a decision can depend on several pieces of information at once.",
                        "if level >= 5 and has_key:\n"
                                + "    print(\"Gate opened\")",
                        125,
                        false,
                        false,
                        lesson11Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 12 — THE ENDLESS GATE
        // -------------------------------------------------

        List<Challenge> lesson12Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "How many times does this loop print?",
                        "count = 0\nwhile count < 3:\n    print(count)\n    count += 1",
                        Arrays.asList(
                                "1",
                                "2",
                                "3",
                                "Infinite"
                        ),
                        "3",
                        "The loop runs for 0, 1, and 2.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.CODE_FIX,
                        "Stop the loop from running forever.",
                        "count = 0\nwhile count < 3:\n    print(count)",
                        null,
                        "count = 0\nwhile count < 3:\n    print(count)\n    count += 1",
                        "The loop needs to change count so the condition eventually becomes false.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Keep asking for a password until the user enters python.",
                        "",
                        null,
                        "password = \"\"\nwhile password != \"python\":\n    password = input()",
                        "A while loop continues as long as its condition remains true.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        12,
                        "The Endless Gate",
                        "Learn how to repeat code while a condition is true.",
                        "A gate blocks your path.\n\n"
                                + "It asks for an access code again and again.\n\n"
                                + "You need a loop.\n\n"
                                + "while repeats a block of code as long as its condition remains true.",
                        "count = 0\nwhile count < 3:\n    print(count)\n    count += 1",
                        150,
                        false,
                        false,
                        lesson12Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 13 — THE MARCH
        // -------------------------------------------------

        List<Challenge> lesson13Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "What will this print?",
                        "for i in range(3):\n    print(i)",
                        Arrays.asList(
                                "0 1 2",
                                "1 2 3",
                                "0 1 2 3",
                                "3"
                        ),
                        "0 1 2",
                        "range(3) produces 0, 1, and 2.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Print the numbers 0 through 4 using a for loop.",
                        "",
                        null,
                        "for i in range(5):\n    print(i)",
                        "range(5) produces the values 0 through 4.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Print the numbers 2, 4, 6, 8 using range().",
                        "",
                        null,
                        "for i in range(2, 10, 2):\n    print(i)",
                        "range(start, stop, step) lets you control where iteration begins and how it changes.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        13,
                        "The March",
                        "Learn how for loops and range() work.",
                        "The machines begin to move.\n\n"
                                + "Instead of waiting for a condition to change, "
                                + "Python can iterate through a sequence of values.\n\n"
                                + "This is what for loops are designed to do.",
                        "for i in range(5):\n    print(i)",
                        150,
                        false,
                        false,
                        lesson13Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 14 — THE MAZE
        // -------------------------------------------------

        List<Challenge> lesson14Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "How many times is ATTACK printed?",
                        "for i in range(3):\n    if i > 0:\n        print(\"ATTACK\")",
                        Arrays.asList(
                                "0",
                                "1",
                                "2",
                                "3"
                        ),
                        "2",
                        "The condition is true for i = 1 and i = 2.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.CODE_FIX,
                        "Fix the indentation of the nested condition.",
                        "for i in range(3):\nif i == 1:\n    print(\"FOUND\")",
                        null,
                        "for i in range(3):\n    if i == 1:\n        print(\"FOUND\")",
                        "Nested blocks require nested indentation.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Print READY only when i is even.",
                        "",
                        null,
                        "for i in range(5):\n    if i % 2 == 0:\n        print(\"READY\")",
                        "You can combine loops, conditions, and operators.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        14,
                        "The Maze",
                        "Combine loops and decisions.",
                        "The path splits into corridors.\n\n"
                                + "Every room has a condition.\n\n"
                                + "Every corridor can contain repeated actions.\n\n"
                                + "Now you must reason about more than one control-flow structure at once.",
                        "for i in range(5):\n"
                                + "    if i % 2 == 0:\n"
                                + "        print(\"READY\")",
                        150,
                        false,
                        false,
                        lesson14Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 15 — THE FAILURE
        // -------------------------------------------------

        List<Challenge> lesson15Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.MULTIPLE_CHOICE,
                        "Which exception is caused by dividing by zero?",
                        "print(10 / 0)",
                        Arrays.asList(
                                "TypeError",
                                "ValueError",
                                "ZeroDivisionError",
                                "NameError"
                        ),
                        "ZeroDivisionError",
                        "Python raises ZeroDivisionError when a number is divided by zero.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Catch a bad integer conversion and print ERROR.",
                        "",
                        null,
                        "try:\n    value = int(input())\nexcept ValueError:\n    print(\"ERROR\")",
                        "try/except allows a program to respond to predictable runtime errors.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_FIX,
                        "Handle invalid number input safely.",
                        "try:\n    age = int(input())\nprint(age)",
                        null,
                        "try:\n    age = int(input())\nexcept ValueError:\n    print(\"Invalid age\")",
                        "Code which may fail should be placed inside the try block and handled with except.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        15,
                        "The Failure",
                        "Learn how Python handles errors without crashing the entire program.",
                        "The system crashes.\n\n"
                                + "But failure does not have to end the program.\n\n"
                                + "Python provides exceptions and try/except so programs can recover from expected problems.",
                        "try:\n"
                                + "    age = int(input())\n"
                                + "except ValueError:\n"
                                + "    print(\"Invalid age\")",
                        150,
                        false,
                        false,
                        lesson15Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 16 — THE ARCHITECT
        // -------------------------------------------------

        List<Challenge> lesson16Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Create a function called greet which prints Hello.",
                        "",
                        null,
                        "def greet():\n    print(\"Hello\")",
                        "Functions package reusable behavior into a named block.",
                        null
                ),

                new Challenge(
                        2,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Create a function greet(name) which prints Hello followed by the name.",
                        "",
                        null,
                        "def greet(name):\n    print(f\"Hello {name}\")",
                        "Parameters allow functions to receive data.",
                        null
                ),

                new Challenge(
                        3,
                        Challenge.ChallengeType.CODE_WRITE,
                        "Create a function add(a, b) that returns the sum.",
                        "",
                        null,
                        "def add(a, b):\n    return a + b",
                        "return sends a value back to the code that called the function.",
                        null
                )
        );

        lessons.add(
                new Lesson(
                        16,
                        "The Architect",
                        "Build reusable pieces of Python code.",
                        "You realize that rebuilding the same machine every time is inefficient.\n\n"
                                + "A better approach is to create reusable commands.\n\n"
                                + "Functions let you package logic and use it repeatedly.",
                        "def add(a, b):\n"
                                + "    return a + b\n\n"
                                + "print(add(2, 3))",
                        200,
                        false,
                        false,
                        lesson16Challenges
                )
        );

        // -------------------------------------------------
        // LESSON 17 — THE FIRST PROGRAM (BOSS)
        // -------------------------------------------------

        List<Challenge> lesson17Challenges = Arrays.asList(

                new Challenge(
                        1,
                        Challenge.ChallengeType.CODE_EXECUTION,

                        "Build a complete character creator.\n\n"
                                + "Your program must:\n"
                                + "1. Ask for a name.\n"
                                + "2. Ask for an age.\n"
                                + "3. Ask the player to choose a class:\n"
                                + "   1 = Warrior\n"
                                + "   2 = Mage\n"
                                + "   3 = Rogue\n"
                                + "4. Convert the age into an integer.\n"
                                + "5. Use a function to print the character summary.\n"
                                + "6. Give the player different starting health based on class.\n\n"
                                + "Output format:\n\n"
                                + "CHARACTER CREATED\n"
                                + "Name: <name>\n"
                                + "Age: <age>\n"
                                + "Class: <class>\n"
                                + "Health: <health>\n"
                                + "Level: 1",

                        "",

                        null,
                        null,

                        "Use input(), int(), conditionals, functions, variables, "
                                + "and formatted strings.",

                        Arrays.asList(

                                new TestCase(
                                        "Nova\n18\n1",
                                        "CHARACTER CREATED\n"
                                                + "Name: Nova\n"
                                                + "Age: 18\n"
                                                + "Class: Warrior\n"
                                                + "Health: 120\n"
                                                + "Level: 1"
                                ),

                                new TestCase(
                                        "Astra\n21\n2",
                                        "CHARACTER CREATED\n"
                                                + "Name: Astra\n"
                                                + "Age: 21\n"
                                                + "Class: Mage\n"
                                                + "Health: 80\n"
                                                + "Level: 1"
                                ),

                                new TestCase(
                                        "Rex\n16\n3",
                                        "CHARACTER CREATED\n"
                                                + "Name: Rex\n"
                                                + "Age: 16\n"
                                                + "Class: Rogue\n"
                                                + "Health: 100\n"
                                                + "Level: 1"
                                )
                        )
                )
        );

        lessons.add(
                new Lesson(
                        17,
                        "The First Program",
                        "Build your first complete Python program.",
                        "The world has changed.\n\n"
                                + "You can store information.\n"
                                + "You can work with different data types.\n"
                                + "You can receive input.\n"
                                + "You can calculate.\n"
                                + "You can make decisions.\n"
                                + "You can repeat actions.\n"
                                + "You can recover from errors.\n"
                                + "And now you can build reusable functions.\n\n"
                                + "The terminal gives you one final challenge.\n\n"
                                + "Build a complete character creator.",

                        "",

                        250,
                        false,
                        false,
                        lesson17Challenges
                )
        );

        return lessons;
    }


    // =====================================================
    // GET LESSON BY ID
    // =====================================================

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
