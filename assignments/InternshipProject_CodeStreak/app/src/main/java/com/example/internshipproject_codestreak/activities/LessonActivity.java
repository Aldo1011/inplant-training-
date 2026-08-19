package com.example.internshipproject_codestreak.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.internshipproject_codestreak.data.LessonCatalog;
import com.example.internshipproject_codestreak.repository.UserRepository;
import com.example.internshipproject_codestreak.viewmodel.Challenge;
import com.example.internshipproject_codestreak.viewmodel.CodeExecutionEngine;
import com.example.internshipproject_codestreak.viewmodel.Lesson;
import com.example.internshipproject_codestreak.viewmodel.TestCase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class LessonActivity extends AppCompatActivity {


    // ---------------------------------------
    // LESSON
    // ---------------------------------------

    private Lesson lesson;

    private int currentChallengeIndex = 0;

    // ---------------------------------------
    // UI
    // ---------------------------------------

    private TextView titleText;
    private TextView descriptionText;
    private TextView contentText;
    private TextView exampleCodeText;
    private TextView progressText;
    private TextView resultText;

    private LinearLayout challengeContainer;

    private Challenge currentChallenge;

    private UserRepository userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userRepository = new UserRepository();

        // ---------------------------------------
        // GET LESSON ID
        // ---------------------------------------

        int lessonId = getIntent().getIntExtra(
                "LESSON_ID",
                -1
        );

        lesson = LessonCatalog.getLessonById(lessonId);

        if (lesson == null) {

            Toast.makeText(
                    this,
                    "Lesson not found.",
                    Toast.LENGTH_LONG
            ).show();

            finish();
            return;
        }

        // ---------------------------------------
        // BUILD UI
        // ---------------------------------------

        buildScreen();

        // ---------------------------------------
        // DISPLAY LESSON
        // ---------------------------------------

        displayLesson();
    }


    // =====================================================
    // BUILD SCREEN
    // =====================================================

    private void buildScreen() {

        ScrollView scrollView = new ScrollView(this);

        LinearLayout root = new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setPadding(
                dp(20),
                dp(20),
                dp(20),
                dp(30)
        );

        scrollView.addView(root);


        // ---------------------------------------
        // TITLE
        // ---------------------------------------

        titleText = new TextView(this);

        titleText.setTextSize(28);

        titleText.setTextColor(
                Color.BLACK
        );

        titleText.setGravity(
                Gravity.CENTER
        );

        root.addView(titleText);


        // ---------------------------------------
        // DESCRIPTION
        // ---------------------------------------

        descriptionText = new TextView(this);

        descriptionText.setTextSize(18);

        descriptionText.setTextColor(
                Color.DKGRAY
        );

        addTopMargin(
                descriptionText,
                12
        );

        root.addView(descriptionText);


        // ---------------------------------------
        // CONTENT
        // ---------------------------------------

        contentText = new TextView(this);

        contentText.setTextSize(17);

        contentText.setTextColor(
                Color.DKGRAY
        );

        addTopMargin(
                contentText,
                20
        );

        root.addView(contentText);


        // ---------------------------------------
        // EXAMPLE LABEL
        // ---------------------------------------

        TextView exampleLabel =
                new TextView(this);

        exampleLabel.setText(
                "Example"
        );

        exampleLabel.setTextSize(20);

        exampleLabel.setTextColor(
                Color.BLACK
        );

        addTopMargin(
                exampleLabel,
                25
        );

        root.addView(exampleLabel);


        // ---------------------------------------
        // EXAMPLE CODE
        // ---------------------------------------

        exampleCodeText =
                new TextView(this);

        exampleCodeText.setTextSize(16);

        exampleCodeText.setTextColor(
                Color.DKGRAY
        );

        addTopMargin(
                exampleCodeText,
                8
        );

        root.addView(exampleCodeText);


        // ---------------------------------------
        // PROGRESS
        // ---------------------------------------

        progressText =
                new TextView(this);

        progressText.setTextSize(16);

        progressText.setTextColor(
                Color.GRAY
        );

        addTopMargin(
                progressText,
                30
        );

        root.addView(progressText);


        // ---------------------------------------
        // CHALLENGE CONTAINER
        // ---------------------------------------

        challengeContainer =
                new LinearLayout(this);

        challengeContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        addTopMargin(
                challengeContainer,
                15
        );

        root.addView(
                challengeContainer
        );


        // ---------------------------------------
        // RESULT
        // ---------------------------------------

        resultText =
                new TextView(this);

        resultText.setTextSize(17);

        resultText.setVisibility(
                View.GONE
        );

        addTopMargin(
                resultText,
                15
        );

        root.addView(resultText);


        // ---------------------------------------
        // SET SCREEN
        // ---------------------------------------

        setContentView(scrollView);
    }


    // =====================================================
    // DISPLAY LESSON
    // =====================================================

    private void displayLesson() {

        titleText.setText(
                lesson.getTitle()
        );

        descriptionText.setText(
                lesson.getDescription()
        );

        contentText.setText(
                lesson.getContent()
        );

        exampleCodeText.setText(
                lesson.getExampleCode()
        );

        showChallenge();
    }


    // =====================================================
    // SHOW CURRENT CHALLENGE
    // =====================================================

    private void showChallenge() {

        challengeContainer.removeAllViews();

        resultText.setVisibility(
                View.GONE
        );

        List<Challenge> challenges =
                lesson.getChallenges();

        // ---------------------------------------
        // CHECK IF LESSON IS FINISHED
        // ---------------------------------------

        if (currentChallengeIndex >=
                challenges.size()) {

            finishLesson();

            return;
        }


        // ---------------------------------------
        // CURRENT CHALLENGE
        // ---------------------------------------

        currentChallenge =
                challenges.get(
                        currentChallengeIndex
                );


        // ---------------------------------------
        // PROGRESS
        // ---------------------------------------

        progressText.setText(
                "Challenge "
                        + (currentChallengeIndex + 1)
                        + " / "
                        + challenges.size()
        );


        // ---------------------------------------
        // QUESTION
        // ---------------------------------------

        TextView question =
                new TextView(this);

        question.setText(
                currentChallenge.getQuestion()
        );

        question.setTextSize(20);

        question.setTextColor(
                Color.BLACK
        );

        challengeContainer.addView(
                question
        );


        // ---------------------------------------
        // CODE
        // ---------------------------------------

        String challengeCode =
                currentChallenge.getCode();

        if (challengeCode != null
                && !challengeCode.isEmpty()) {

            TextView code =
                    new TextView(this);

            code.setText(
                    challengeCode
            );

            code.setTextSize(16);

            code.setTextColor(
                    Color.DKGRAY
            );

            addTopMargin(
                    code,
                    15
            );

            challengeContainer.addView(
                    code
            );
        }


        // ---------------------------------------
        // CHALLENGE TYPE
        // ---------------------------------------

        switch (
                currentChallenge.getType()
        ) {

            case MULTIPLE_CHOICE:

                buildMultipleChoice();

                break;


            case CODE_WRITE:

            case CODE_FIX:

                buildCodeChallenge();

                break;


            case CODE_OUTPUT:

                buildOutputChallenge();

                break;

                case CODE_EXECUTION:
                buildCodeExecutionChallenge();
                break;
        }
    }


    // =====================================================
    // MULTIPLE CHOICE
    // =====================================================

    private void buildMultipleChoice() {

        RadioGroup radioGroup =
                new RadioGroup(this);

        List<String> options =
                currentChallenge.getOptions();

        if (options != null) {

            for (String option : options) {

                RadioButton radioButton =
                        new RadioButton(this);

                radioButton.setText(
                        option
                );

                radioButton.setTextSize(
                        17
                );

                radioGroup.addView(
                        radioButton
                );
            }
        }

        addTopMargin(
                radioGroup,
                15
        );

        challengeContainer.addView(
                radioGroup
        );


        // ---------------------------------------
        // SUBMIT
        // ---------------------------------------

        Button submitButton =
                createButton("Submit");

        challengeContainer.addView(
                submitButton
        );


        submitButton.setOnClickListener(
                v -> {

                    int selectedId =
                            radioGroup
                                    .getCheckedRadioButtonId();

                    if (selectedId == -1) {

                        Toast.makeText(
                                this,
                                "Choose an answer first.",
                                Toast.LENGTH_SHORT
                        ).show();

                        return;
                    }

                    RadioButton selected =
                            radioGroup.findViewById(
                                    selectedId
                            );

                    checkAnswer(
                            selected
                                    .getText()
                                    .toString()
                    );
                }
        );
    }


    // =====================================================
    // CODE WRITE / CODE FIX
    // =====================================================

    private void buildCodeChallenge() {

        EditText codeInput =
                new EditText(this);

        codeInput.setHint(
                "Write your code here..."
        );

        codeInput.setTextSize(16);

        codeInput.setGravity(
                Gravity.TOP | Gravity.START
        );

        codeInput.setMinLines(5);

        codeInput.setInputType(
                InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_FLAG_MULTI_LINE
        );

        addTopMargin(
                codeInput,
                15
        );

        challengeContainer.addView(
                codeInput
        );


        // ---------------------------------------
        // RUN BUTTON
        // ---------------------------------------

        Button runButton =
                createButton("Run");

        challengeContainer.addView(
                runButton
        );


        runButton.setOnClickListener(
                v -> {

                    String answer =
                            codeInput
                                    .getText()
                                    .toString();

                    checkAnswer(answer);
                }
        );
    }


    // =====================================================
    // OUTPUT CHALLENGE
    // =====================================================

    private void buildOutputChallenge() {

        EditText answerInput =
                new EditText(this);

        answerInput.setHint(
                "Enter the output..."
        );

        answerInput.setTextSize(17);

        addTopMargin(
                answerInput,
                15
        );

        challengeContainer.addView(
                answerInput
        );


        // ---------------------------------------
        // CHECK BUTTON
        // ---------------------------------------

        Button checkButton =
                createButton("Check");

        challengeContainer.addView(
                checkButton
        );


        checkButton.setOnClickListener(
                v -> {

                    String answer =
                            answerInput
                                    .getText()
                                    .toString();

                    checkAnswer(answer);
                }
        );
    }


    // =====================================================
    // CHECK ANSWER
    // =====================================================

    private void checkAnswer(String answer) {

        String userAnswer = normalize(answer);

        String expectedAnswer = normalize(
                currentChallenge.getExpectedAnswer()
        );

        boolean correct = userAnswer.equals(expectedAnswer);

        if (correct) {

            resultText.setText(
                    "✓ Correct!\n\n"
                            + currentChallenge.getExplanation()
            );

            resultText.setTextColor(
                    Color.rgb(30, 120, 60)
            );

        } else {

            resultText.setText(
                    "✗ Incorrect\n\n"
                            + "Correct answer:\n"
                            + currentChallenge.getExpectedAnswer()
                            + "\n\n"
                            + currentChallenge.getExplanation()
            );

            resultText.setTextColor(
                    Color.rgb(180, 40, 40)
            );
        }

        resultText.setVisibility(View.VISIBLE);

        Button nextButton = createButton("Continue");

        challengeContainer.addView(nextButton);

        nextButton.setOnClickListener(v -> {

            currentChallengeIndex++;

            showChallenge();
        });
    }


    // =====================================================
    // LESSON COMPLETE
    // =====================================================

    private void finishLesson() {

        challengeContainer.removeAllViews();

        progressText.setText(
                "Lesson Complete!"
        );

        TextView completion = new TextView(this);

        completion.setText(
                "Completing lesson..."
        );

        completion.setTextSize(22);

        completion.setTextColor(
                Color.BLACK
        );

        challengeContainer.addView(
                completion
        );

        FirebaseAuth auth =
                FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {

            Toast.makeText(
                    this,
                    "Could not find logged-in user.",
                    Toast.LENGTH_LONG
            ).show();

            return;
        }

        String uid =
                auth.getCurrentUser().getUid();

        userRepository.getUser(uid)
                .addOnSuccessListener(user -> {

                    if (user == null) {

                        Toast.makeText(
                                this,
                                "User data not found.",
                                Toast.LENGTH_LONG
                        ).show();

                        return;
                    }

                    // ---------------------------------------
                    // ADD XP
                    // ---------------------------------------

                    user.setXp(
                            user.getXp()
                                    + lesson.getXpReward()
                    );

                    // ---------------------------------------
                    // REMOVE ONE HEART
                    // ---------------------------------------

                    user.setHearts(
                            Math.max(
                                    0,
                                    user.getHearts() - 1
                            )
                    );

                    //get completed lesson id

                    user.completeLesson(
                            lesson.getId()
                    );

                    userRepository.updateStreak(user);

                    // ---------------------------------------
                    // SAVE USER
                    // ---------------------------------------

                    userRepository.updateUser(
                            uid,
                            user
                    ).addOnSuccessListener(unused -> {

                        completion.setText(
                                "🎉 You completed "
                                        + lesson.getTitle()
                                        + "!\n\n"
                                        + "+"
                                        + lesson.getXpReward()
                                        + " XP"
                                        + "\n\n"
                                        + "❤️ Hearts remaining: "
                                        + user.getHearts()
                                        + "\n"
                                        + "🔥 Streak: "
                                        + user.getStreak()
                        );

                        Button backButton =
                                createButton(
                                        "Back to Map"
                                );

                        challengeContainer.addView(
                                backButton
                        );

                        backButton.setOnClickListener(
                                v -> finish()
                        );

                    }).addOnFailureListener(e -> {

                        completion.setText(
                                "Lesson completed, "
                                        + "but your progress "
                                        + "could not be saved."
                        );

                        Toast.makeText(
                                this,
                                "Failed to save progress.",
                                Toast.LENGTH_LONG
                        ).show();
                    });

                })
                .addOnFailureListener(e -> {

                    completion.setText(
                            "Could not load your progress."
                    );

                    Toast.makeText(
                            this,
                            "Failed to load user data.",
                            Toast.LENGTH_LONG
                    ).show();
                });
    }


    // =====================================================
    // CREATE BUTTON
    // =====================================================

    private Button createButton(
            String text
    ) {

        Button button =
                new Button(this);

        button.setText(text);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.topMargin =
                dp(15);

        button.setLayoutParams(
                params
        );

        return button;
    }


    // =====================================================
    // NORMALIZE ANSWER
    // =====================================================

    private String normalize(
            String text
    ) {

        if (text == null) {
            return "";
        }

        return text
                .trim()
                .replace("\r\n", "\n")
                .replace("\r", "\n");
    }


    // =====================================================
    // ADD TOP MARGIN
    // =====================================================

    private void addTopMargin(
            View view,
            int marginDp
    ) {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.topMargin =
                dp(marginDp);

        view.setLayoutParams(
                params
        );
    }


    // =====================================================
    // DP CONVERSION
    // =====================================================

    private int dp(
            int value
    ) {

        return (int) (
                value
                        * getResources()
                        .getDisplayMetrics()
                        .density
                        + 0.5f
        );
    }

    private void buildCodeExecutionChallenge() {

        EditText codeInput = new EditText(this);

        codeInput.setHint(
                "Write your Python program here..."
        );

        codeInput.setTextSize(16);

        codeInput.setGravity(
                Gravity.TOP | Gravity.START
        );

        codeInput.setMinLines(10);

        codeInput.setInputType(
                InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_FLAG_MULTI_LINE
        );

        addTopMargin(codeInput, 15);

        challengeContainer.addView(codeInput);

        Button runButton =
                createButton("Run");

        challengeContainer.addView(runButton);

        runButton.setOnClickListener(v -> {

            String code =
                    codeInput.getText()
                            .toString();

            executeBossCode(
                    code,
                    runButton
            );
        });
    }

    private void executeBossCode(
            String code,
            Button runButton
    ) {

        List<TestCase> testCases =
                currentChallenge.getTestCases();

        if (testCases == null
                || testCases.isEmpty()) {

            resultText.setText(
                    "No test cases configured."
            );

            resultText.setVisibility(
                    View.VISIBLE
            );

            return;
        }

        runButton.setEnabled(false);

        boolean allPassed = true;

        StringBuilder result =
                new StringBuilder();

        for (int i = 0;
             i < testCases.size();
             i++) {

            TestCase testCase =
                    testCases.get(i);

            CodeExecutionEngine.ExecutionResult executionResult =
                    CodeExecutionEngine.execute(
                            this,
                            code,
                            testCase.getInput()
                    );

            String actualOutput =
                    normalize(
                            executionResult.getOutput()
                    );

            String expectedOutput =
                    normalize(
                            testCase.getExpectedOutput()
                    );

            if (!executionResult.isSuccess()) {

                allPassed = false;

                result.append(
                        "Test "
                                + (i + 1)
                                + " failed.\n\n"
                );

                result.append(
                        "Error:\n"
                                + executionResult.getError()
                                + "\n\n"
                );

                break;
            }

            if (!actualOutput.equals(
                    expectedOutput
            )) {

                allPassed = false;

                result.append(
                        "Test "
                                + (i + 1)
                                + " failed.\n\n"
                );

                result.append(
                        "Expected:\n"
                                + expectedOutput
                                + "\n\n"
                );

                result.append(
                        "Got:\n"
                                + actualOutput
                                + "\n\n"
                );

                break;
            }

            result.append(
                    "✓ Test "
                            + (i + 1)
                            + " passed.\n"
            );
        }

        if (allPassed) {

            result.insert(
                    0,
                    "🎉 All tests passed!\n\n"
            );

            result.append(
                    "\n"
                            + currentChallenge
                            .getExplanation()
            );

        } else {

            result.insert(
                    0,
                    "✗ Your solution didn't pass all tests.\n\n"
            );
        }

        resultText.setText(
                result.toString()
        );

        resultText.setVisibility(
                View.VISIBLE
        );

        runButton.setEnabled(true);

        if (allPassed) {

            Button continueButton =
                    createButton("Continue");

            challengeContainer.addView(
                    continueButton
            );

            continueButton.setOnClickListener(v -> {

                currentChallengeIndex++;

                showChallenge();
            });
        }
    }


}
