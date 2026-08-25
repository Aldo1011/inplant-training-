package com.example.internshipproject_codestreak.activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.MotionEvent;
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

import com.example.internshipproject_codestreak.R;
import com.example.internshipproject_codestreak.data.LessonCatalog;
import com.example.internshipproject_codestreak.repository.UserRepository;
import com.example.internshipproject_codestreak.viewmodel.Challenge;
import com.example.internshipproject_codestreak.viewmodel.CodeExecutionEngine;
import com.example.internshipproject_codestreak.viewmodel.Lesson;
import com.example.internshipproject_codestreak.viewmodel.TestCase;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class LessonActivity extends AppCompatActivity {

    // ---------------------------------------
    // LESSON
    // ---------------------------------------

    private Lesson lesson;

    private int currentChallengeIndex = 0;
    private int wrongAnswers = 0;
    private int answeredQuestions = 0;

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

        ScrollView scrollView =
                new ScrollView(this);

        scrollView.setFillViewport(true);

        scrollView.setBackgroundColor(
                getResources().getColor(
                        R.color.cs_secondary
                )
        );


        LinearLayout root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setPadding(
                dp(18),
                dp(14),
                dp(18),
                dp(30)
        );


        // =================================================
        // HEADER
        // =================================================

        LinearLayout header =
                new LinearLayout(this);

        header.setOrientation(
                LinearLayout.HORIZONTAL
        );

        header.setGravity(
                Gravity.CENTER_VERTICAL
        );


        // Back button

        TextView backButton =
                new TextView(this);

        backButton.setText("‹");

        backButton.setTextSize(36);

        backButton.setTextColor(
                getResources().getColor(
                        R.color.cs_primary
                )
        );

        backButton.setGravity(
                Gravity.CENTER
        );

        backButton.setClickable(true);
        backButton.setFocusable(true);

        backButton.setOnClickListener(
                v -> finish()
        );

        header.addView(
                backButton,
                new LinearLayout.LayoutParams(
                        dp(42),
                        dp(48)
                )
        );


        // Center title

        LinearLayout titleArea =
                new LinearLayout(this);

        titleArea.setOrientation(
                LinearLayout.VERTICAL
        );

        titleArea.setGravity(
                Gravity.CENTER
        );


        titleText =
                new TextView(this);

        titleText.setTextSize(21);

        titleText.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        titleText.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        titleText.setGravity(
                Gravity.CENTER
        );

        titleArea.addView(
                titleText
        );


        TextView subtitle =
                new TextView(this);

        subtitle.setText(
                "PYTHON LESSON"
        );

        subtitle.setTextSize(10);

        subtitle.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        subtitle.setTextColor(
                getResources().getColor(
                        R.color.cs_text_muted
                )
        );

        subtitle.setGravity(
                Gravity.CENTER
        );

        titleArea.addView(
                subtitle
        );


        header.addView(
                titleArea,
                new LinearLayout.LayoutParams(
                        0,
                        dp(48),
                        1f
                )
        );


        // Right spacer keeps title centered

        View rightSpacer =
                new View(this);

        header.addView(
                rightSpacer,
                new LinearLayout.LayoutParams(
                        dp(42),
                        dp(48)
                )
        );


        root.addView(header);


        // =================================================
        // PROGRESS
        // =================================================

        progressText =
                new TextView(this);

        progressText.setTextSize(12);

        progressText.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        progressText.setTextColor(
                getResources().getColor(
                        R.color.cs_primary
                )
        );

        progressText.setGravity(
                Gravity.CENTER
        );

        progressText.setPadding(
                dp(14),
                dp(8),
                dp(14),
                dp(8)
        );

        progressText.setBackground(
                getRoundedBackground(
                        Color.WHITE,
                        dp(20)
                )
        );

        LinearLayout.LayoutParams
                progressParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        progressParams.gravity =
                Gravity.CENTER_HORIZONTAL;

        progressParams.topMargin =
                dp(10);

        root.addView(
                progressText,
                progressParams
        );


        // =================================================
        // CONCEPT CARD
        // =================================================

        LinearLayout conceptCard =
                createContentCard();


        TextView conceptLabel =
                createSectionLabel(
                        "💡  CONCEPT"
                );

        conceptCard.addView(
                conceptLabel
        );


        descriptionText =
                new TextView(this);

        descriptionText.setTextSize(16);

        descriptionText.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        descriptionText.setLineSpacing(
                dp(2),
                1f
        );


        addTopMargin(
                descriptionText,
                10
        );

        conceptCard.addView(
                descriptionText
        );


        addTopMargin(
                conceptCard,
                20
        );

        root.addView(
                conceptCard
        );


        // =================================================
        // LEARN CARD
        // =================================================

        LinearLayout learnCard =
                createContentCard();


        TextView learnLabel =
                createSectionLabel(
                        "📖  LEARN"
                );

        learnCard.addView(
                learnLabel
        );


        contentText =
                new TextView(this);

        contentText.setTextSize(16);

        contentText.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        contentText.setLineSpacing(
                dp(3),
                1f
        );


        addTopMargin(
                contentText,
                10
        );

        learnCard.addView(
                contentText
        );


        addTopMargin(
                learnCard,
                12
        );

        root.addView(
                learnCard
        );


        // =================================================
        // EXAMPLE
        // =================================================

        TextView exampleLabel =
                createSectionLabel(
                        "💻  EXAMPLE"
                );

        addTopMargin(
                exampleLabel,
                24
        );

        root.addView(
                exampleLabel
        );


        exampleCodeText =
                new TextView(this);

        exampleCodeText.setTextSize(15);

        exampleCodeText.setTypeface(
                Typeface.MONOSPACE
        );

        exampleCodeText.setTextColor(
                Color.WHITE
        );

        exampleCodeText.setGravity(
                Gravity.START
        );

        exampleCodeText.setPadding(
                dp(18),
                dp(16),
                dp(18),
                dp(16)
        );

        exampleCodeText.setBackground(
                getRoundedBackground(
                        Color.rgb(25, 32, 55),
                        dp(18)
                )
        );


        addTopMargin(
                exampleCodeText,
                10
        );

        root.addView(
                exampleCodeText
        );


        // =================================================
        // YOUR TURN
        // =================================================

        TextView challengeLabel =
                createSectionLabel(
                        "🎯  YOUR TURN"
                );

        addTopMargin(
                challengeLabel,
                28
        );

        root.addView(
                challengeLabel
        );


        challengeContainer =
                new LinearLayout(this);

        challengeContainer.setOrientation(
                LinearLayout.VERTICAL
        );


        addTopMargin(
                challengeContainer,
                10
        );

        root.addView(
                challengeContainer
        );


        // =================================================
        // RESULT
        // =================================================

        resultText =
                new TextView(this);

        resultText.setTextSize(16);

        resultText.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        resultText.setPadding(
                dp(16),
                dp(14),
                dp(16),
                dp(14)
        );

        resultText.setBackground(
                getRoundedBackground(
                        Color.WHITE,
                        dp(16)
                )
        );

        resultText.setVisibility(
                View.GONE
        );


        addTopMargin(
                resultText,
                16
        );

        root.addView(
                resultText
        );


        scrollView.addView(root);

        setContentView(scrollView);
    }


    // =====================================================
    // CONTENT CARD
    // =====================================================

    private LinearLayout createContentCard() {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                dp(18),
                dp(18),
                dp(18),
                dp(18)
        );

        card.setBackground(
                getRoundedBackground(
                        Color.WHITE,
                        dp(20)
                )
        );

        card.setElevation(
                dp(2)
        );

        return card;
    }


    // =====================================================
    // SECTION LABEL
    // =====================================================

    private TextView createSectionLabel(
            String text
    ) {

        TextView label =
                new TextView(this);

        label.setText(text);

        label.setTextSize(12);

        label.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        label.setTextColor(
                getResources().getColor(
                        R.color.cs_primary
                )
        );

        return label;
    }


    // =====================================================
    // ROUNDED BACKGROUND
    // =====================================================

    private android.graphics.drawable.GradientDrawable
    getRoundedBackground(
            int color,
            int radius
    ) {

        android.graphics.drawable.GradientDrawable
                background =
                new android.graphics.drawable.GradientDrawable();

        background.setColor(color);

        background.setCornerRadius(
                radius
        );

        return background;
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

        // Subtle entrance animation only.
        challengeContainer.setAlpha(0f);

        challengeContainer.setTranslationY(
                dp(12)
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

        question.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        question.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        question.setLineSpacing(
                dp(2),
                1f
        );

        question.setPadding(
                dp(18),
                dp(18),
                dp(18),
                dp(18)
        );

        question.setBackground(
                getRoundedBackground(
                        Color.WHITE,
                        dp(18)
                )
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

            code.setTextSize(15);

            code.setTypeface(
                    Typeface.MONOSPACE
            );

            code.setTextColor(
                    Color.WHITE
            );

            code.setPadding(
                    dp(16),
                    dp(14),
                    dp(16),
                    dp(14)
            );

            code.setBackground(
                    getRoundedBackground(
                            Color.rgb(25, 32, 55),
                            dp(16)
                    )
            );

            addTopMargin(
                    code,
                    12
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


        // Visual-only entrance animation.
        challengeContainer.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(220)
                .start();
    }


    // =====================================================
    // MULTIPLE CHOICE
    // =====================================================

    private void buildMultipleChoice() {

        RadioGroup radioGroup =
                new RadioGroup(this);

        radioGroup.setOrientation(
                RadioGroup.VERTICAL
        );


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
                        16
                );

                radioButton.setTextColor(
                        getResources().getColor(
                                R.color.cs_text
                        )
                );

                radioButton.setPadding(
                        dp(14),
                        dp(12),
                        dp(14),
                        dp(12)
                );

                radioButton.setButtonTintList(
                        ColorStateList.valueOf(
                                getResources().getColor(
                                        R.color.cs_primary
                                )
                        )
                );


                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                params.bottomMargin =
                        dp(8);


                radioGroup.addView(
                        radioButton,
                        params
                );
            }
        }


        addTopMargin(
                radioGroup,
                12
        );

        challengeContainer.addView(
                radioGroup
        );


        Button submitButton =
                createButton(
                        "Check Answer"
                );

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

        codeInput.setTextSize(15);

        codeInput.setTypeface(
                Typeface.MONOSPACE
        );

        codeInput.setGravity(
                Gravity.TOP | Gravity.START
        );

        codeInput.setMinLines(5);

        codeInput.setInputType(
                InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_FLAG_MULTI_LINE
        );

        codeInput.setPadding(
                dp(16),
                dp(16),
                dp(16),
                dp(16)
        );

        codeInput.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        codeInput.setHintTextColor(
                getResources().getColor(
                        R.color.cs_text_muted
                )
        );

        codeInput.setBackground(
                getRoundedBackground(
                        Color.WHITE,
                        dp(18)
                )
        );


        addTopMargin(
                codeInput,
                12
        );

        challengeContainer.addView(
                codeInput
        );


        Button runButton =
                createButton(
                        "Check Answer"
                );

        challengeContainer.addView(
                runButton
        );


        runButton.setOnClickListener(
                v -> {

                    String answer =
                            codeInput
                                    .getText()
                                    .toString();

                    checkAnswer(
                            answer
                    );
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
                "Enter the expected output..."
        );

        answerInput.setTextSize(16);

        answerInput.setTypeface(
                Typeface.MONOSPACE
        );

        answerInput.setGravity(
                Gravity.TOP | Gravity.START
        );

        answerInput.setMinLines(3);

        answerInput.setPadding(
                dp(16),
                dp(14),
                dp(16),
                dp(14)
        );

        answerInput.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        answerInput.setHintTextColor(
                getResources().getColor(
                        R.color.cs_text_muted
                )
        );

        answerInput.setBackground(
                getRoundedBackground(
                        Color.WHITE,
                        dp(18)
                )
        );


        addTopMargin(
                answerInput,
                12
        );

        challengeContainer.addView(
                answerInput
        );


        Button checkButton =
                createButton(
                        "Check Answer"
                );

        challengeContainer.addView(
                checkButton
        );


        checkButton.setOnClickListener(
                v -> {

                    String answer =
                            answerInput
                                    .getText()
                                    .toString();

                    checkAnswer(
                            answer
                    );
                }
        );
    }


    // =====================================================
    // CHECK ANSWER
    // =====================================================

    private void checkAnswer(
            String answer
    ) {

        String userAnswer =
                normalize(answer);

        String expectedAnswer =
                normalize(
                        currentChallenge
                                .getExpectedAnswer()
                );

        boolean correct =
                userAnswer.equals(
                        expectedAnswer
                );

        showExplanationSheet(
                correct
        );
    }


    // =====================================================
    // LESSON COMPLETE
    // =====================================================

    private void finishLesson() {

        FirebaseAuth auth =
                FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {

            Toast.makeText(
                    this,
                    "Could not find logged-in user.",
                    Toast.LENGTH_LONG
            ).show();

            finish();

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

                        finish();

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


                    // ---------------------------------------
                    // COMPLETE LESSON
                    // ---------------------------------------

                    user.completeLesson(
                            lesson.getId()
                    );


                    // ---------------------------------------
                    // UPDATE STREAK
                    // ---------------------------------------

                    userRepository.updateStreak(
                            user
                    );


                    // ---------------------------------------
                    // SAVE USER
                    // ---------------------------------------

                    userRepository.updateUser(
                            uid,
                            user
                    ).addOnSuccessListener(
                            unused -> {

                                showLessonCompletionSheet(
                                        user.getXp(),
                                        lesson.getXpReward(),
                                        user.getStreak(),
                                        user.getHearts()
                                );

                            }
                    ).addOnFailureListener(
                            e -> {

                                Toast.makeText(
                                        this,
                                        "Failed to save progress.",
                                        Toast.LENGTH_LONG
                                ).show();

                            }
                    );

                })
                .addOnFailureListener(
                        e -> {

                            Toast.makeText(
                                    this,
                                    "Failed to load user data.",
                                    Toast.LENGTH_LONG
                            ).show();

                        }
                );
    }

    private void showLessonCompletionSheet(
            int totalXp,
            int xpGained,
            int streak,
            int hearts
    ) {

        BottomSheetDialog dialog =
                new BottomSheetDialog(this);


        LinearLayout container =
                new LinearLayout(this);

        container.setOrientation(
                LinearLayout.VERTICAL
        );

        container.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        container.setPadding(
                dp(24),
                dp(24),
                dp(24),
                dp(30)
        );

        container.setBackground(
                getRoundedTopBackground()
        );


        // ---------------------------------------
        // CONGRATULATIONS
        // ---------------------------------------

        TextView title =
                new TextView(this);

        title.setText(
                "🎉 Lesson Complete!"
        );

        title.setTextSize(26);

        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        title.setTextColor(
                getResources().getColor(
                        R.color.cs_primary
                )
        );

        title.setGravity(
                Gravity.CENTER
        );

        container.addView(
                title
        );


        // ---------------------------------------
        // LESSON NAME
        // ---------------------------------------

        TextView lessonName =
                new TextView(this);

        lessonName.setText(
                lesson.getTitle()
        );

        lessonName.setTextSize(16);

        lessonName.setTextColor(
                getResources().getColor(
                        R.color.cs_text_muted
                )
        );

        lessonName.setGravity(
                Gravity.CENTER
        );


        LinearLayout.LayoutParams
                lessonNameParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        lessonNameParams.topMargin =
                dp(6);

        container.addView(
                lessonName,
                lessonNameParams
        );


        // ---------------------------------------
        // XP
        // ---------------------------------------

        TextView xpText =
                new TextView(this);

        xpText.setText(
                "⭐  +" + xpGained + " XP"
        );

        xpText.setTextSize(21);

        xpText.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        xpText.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        xpText.setGravity(
                Gravity.CENTER
        );


        LinearLayout.LayoutParams
                xpParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        xpParams.topMargin =
                dp(24);

        container.addView(
                xpText,
                xpParams
        );


        // ---------------------------------------
        // STREAK
        // ---------------------------------------

        TextView streakText =
                new TextView(this);

        streakText.setText(
                "🔥  Streak: " + streak
        );

        streakText.setTextSize(18);

        streakText.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        streakText.setGravity(
                Gravity.CENTER
        );


        LinearLayout.LayoutParams
                streakParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        streakParams.topMargin =
                dp(12);

        container.addView(
                streakText,
                streakParams
        );


        // ---------------------------------------
        // HEARTS
        // ---------------------------------------

        TextView heartsText =
                new TextView(this);

        heartsText.setText(
                "❤️  Hearts remaining: " + hearts
        );

        heartsText.setTextSize(16);

        heartsText.setTextColor(
                getResources().getColor(
                        R.color.cs_text_muted
                )
        );

        heartsText.setGravity(
                Gravity.CENTER
        );


        LinearLayout.LayoutParams
                heartsParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        heartsParams.topMargin =
                dp(8);

        container.addView(
                heartsText,
                heartsParams
        );


        // ---------------------------------------
        // CONTINUE
        // ---------------------------------------

        Button continueButton =
                createButton(
                        "Back to Map"
                );


        LinearLayout.LayoutParams
                buttonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        buttonParams.topMargin =
                dp(26);

        container.addView(
                continueButton,
                buttonParams
        );


        continueButton.setOnClickListener(
                v -> {

                    dialog.dismiss();

                    finish();
                }
        );


        dialog.setContentView(
                container
        );

        dialog.show();
    }


    // =====================================================
    // CREATE BUTTON
    // =====================================================

    private Button createButton(
            String text
    ) {

        Button button =
                new Button(this);

        button.setText(
                text
        );

        button.setTextSize(
                15
        );

        button.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        button.setTextColor(
                Color.WHITE
        );

        button.setAllCaps(
                false
        );

        button.setPadding(
                dp(16),
                dp(4),
                dp(16),
                dp(4)
        );

        button.setBackground(
                getRoundedBackground(
                        getResources().getColor(
                                R.color.cs_primary
                        ),
                        dp(18)
                )
        );


        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(54)
                );

        params.topMargin =
                dp(15);

        button.setLayoutParams(
                params
        );


        button.setOnTouchListener(
                (v, event) -> {

                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            v.animate()
                                    .scaleX(0.97f)
                                    .scaleY(0.97f)
                                    .setDuration(70)
                                    .start();

                            break;


                        case MotionEvent.ACTION_UP:

                        case MotionEvent.ACTION_CANCEL:

                            v.animate()
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .setDuration(70)
                                    .start();

                            break;
                    }

                    return false;
                }
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


    // =====================================================
    // BOSS CODE EXECUTION
    // =====================================================

    private void buildCodeExecutionChallenge() {

        TextView bossLabel =
                createSectionLabel(
                        "⚔  BOSS CODE EDITOR"
                );

        bossLabel.setTextColor(
                Color.rgb(
                        210,
                        70,
                        70
                )
        );

        addTopMargin(
                bossLabel,
                16
        );

        challengeContainer.addView(
                bossLabel
        );


        EditText codeInput =
                new EditText(this);

        codeInput.setHint(
                "Write your Python program here..."
        );

        codeInput.setTextSize(15);

        codeInput.setTypeface(
                Typeface.MONOSPACE
        );

        codeInput.setGravity(
                Gravity.TOP | Gravity.START
        );

        codeInput.setMinLines(10);

        codeInput.setInputType(
                InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_FLAG_MULTI_LINE
        );

        codeInput.setPadding(
                dp(16),
                dp(16),
                dp(16),
                dp(16)
        );

        codeInput.setTextColor(
                Color.WHITE
        );

        codeInput.setHintTextColor(
                Color.rgb(
                        180,
                        185,
                        200
                )
        );

        codeInput.setBackground(
                getRoundedBackground(
                        Color.rgb(25, 32, 55),
                        dp(18)
                )
        );


        addTopMargin(
                codeInput,
                10
        );

        challengeContainer.addView(
                codeInput
        );


        Button runButton =
                createButton(
                        "▶  Run Code"
                );

        challengeContainer.addView(
                runButton
        );


        runButton.setOnClickListener(
                v -> {

                    String code =
                            codeInput.getText()
                                    .toString();

                    executeBossCode(
                            code,
                            runButton
                    );
                }
        );
    }


    // =====================================================
    // EXECUTE BOSS CODE
    // =====================================================

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


        boolean allPassed =
                true;


        StringBuilder result =
                new StringBuilder();


        for (int i = 0;
             i < testCases.size();
             i++) {

            TestCase testCase =
                    testCases.get(i);


            CodeExecutionEngine.ExecutionResult
                    executionResult =
                    CodeExecutionEngine.execute(
                            this,
                            code,
                            testCase.getInput()
                    );


            String actualOutput =
                    normalize(
                            executionResult
                                    .getOutput()
                    );


            String expectedOutput =
                    normalize(
                            testCase
                                    .getExpectedOutput()
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
                                + executionResult
                                .getError()
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


        runButton.setEnabled(true);

        showBossResultSheet(
                allPassed,
                result.toString()
        );
    }

    private void showBossResultSheet(
            boolean passed,
            String resultText
    ) {

        BottomSheetDialog dialog =
                new BottomSheetDialog(this);

        LinearLayout container =
                new LinearLayout(this);

        container.setOrientation(
                LinearLayout.VERTICAL
        );

        container.setPadding(
                dp(24),
                dp(20),
                dp(24),
                dp(28)
        );

        container.setBackground(
                getRoundedTopBackground()
        );


        // ---------------------------------------
        // TITLE
        // ---------------------------------------

        TextView title =
                new TextView(this);

        title.setText(
                passed
                        ? "🎉 Boss Defeated!"
                        : "⚔ Boss Failed"
        );

        title.setTextSize(24);

        title.setTypeface(
                null,
                Typeface.BOLD
        );

        title.setTextColor(
                passed
                        ? Color.rgb(30, 150, 75)
                        : Color.rgb(200, 60, 60)
        );

        container.addView(title);


        // ---------------------------------------
        // RESULT
        // ---------------------------------------

        TextView output =
                new TextView(this);

        if (passed) {

            output.setText(
                    resultText
            );

        } else {

            output.setText(
                    resultText
                            + "\n\n"
                            + "Correct approach:\n"
                            + currentChallenge
                            .getExplanation()
            );
        }

        output.setTextSize(15);

        output.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );

        output.setTypeface(
                Typeface.MONOSPACE
        );

        LinearLayout.LayoutParams
                outputParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        outputParams.topMargin =
                dp(16);

        container.addView(
                output,
                outputParams
        );


        // ---------------------------------------
        // ACTION
        // ---------------------------------------

        Button actionButton =
                createButton(
                        passed
                                ? "Continue"
                                : "Back to Map"
                );

        container.addView(
                actionButton
        );


        actionButton.setOnClickListener(
                v -> {

                    dialog.dismiss();

                    if (passed) {

                        // Boss passed:
                        // move to the next challenge.
                        currentChallengeIndex++;

                        showChallenge();

                    } else {

                        // Boss failed:
                        // do NOT advance.
                        // Simply return to the map.
                        finish();
                    }
                }
        );


        dialog.setContentView(
                container
        );

        dialog.show();
    }


    // =====================================================
    // EXPLANATION BOTTOM SHEET
    // =====================================================

    private void showExplanationSheet(
            boolean correct
    ) {

        BottomSheetDialog dialog =
                new BottomSheetDialog(this);


        LinearLayout container =
                new LinearLayout(this);

        container.setOrientation(
                LinearLayout.VERTICAL
        );

        container.setPadding(
                dp(24),
                dp(18),
                dp(24),
                dp(26)
        );


        container.setBackground(
                getRoundedTopBackground()
        );


        // ---------------------------------------
        // RESULT
        // ---------------------------------------

        TextView result =
                new TextView(this);

        result.setText(
                correct
                        ? "✓ Correct!"
                        : "✗ Not quite"
        );

        result.setTextSize(
                23
        );

        result.setTypeface(
                null,
                Typeface.BOLD
        );

        result.setTextColor(
                correct
                        ? Color.rgb(
                        30,
                        150,
                        75
                )
                        : Color.rgb(
                        200,
                        50,
                        50
                )
        );


        container.addView(
                result
        );


        // ---------------------------------------
        // EXPLANATION
        // ---------------------------------------

        TextView explanation =
                new TextView(this);

        explanation.setText(
                correct
                        ? currentChallenge
                          .getExplanation()

                        : "Correct answer:\n"
                          + currentChallenge
                            .getExpectedAnswer()
                          + "\n\n"
                          + currentChallenge
                            .getExplanation()
        );


        explanation.setTextSize(
                16
        );

        explanation.setTextColor(
                getResources().getColor(
                        R.color.cs_text
                )
        );


        LinearLayout.LayoutParams
                explanationParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );


        explanationParams.topMargin =
                dp(14);


        container.addView(
                explanation,
                explanationParams
        );


        // ---------------------------------------
        // CONTINUE
        // ---------------------------------------

        Button continueButton =
                createButton(
                        "Continue"
                );


        LinearLayout.LayoutParams
                buttonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );


        buttonParams.topMargin =
                dp(20);


        container.addView(
                continueButton,
                buttonParams
        );


        continueButton.setOnClickListener(
                v -> {

                    dialog.dismiss();

                    currentChallengeIndex++;

                    showChallenge();
                }
        );


        dialog.setContentView(
                container
        );


        dialog.show();
    }


    // =====================================================
    // BOTTOM SHEET BACKGROUND
    // =====================================================

    private android.graphics.drawable.GradientDrawable
    getRoundedTopBackground() {

        android.graphics.drawable.GradientDrawable
                background =
                new android.graphics.drawable.GradientDrawable();

        background.setColor(
                Color.WHITE
        );

        background.setCornerRadii(
                new float[]{
                        dp(26), dp(26),
                        dp(26), dp(26),
                        0, 0,
                        0, 0
                }
        );

        return background;
    }
}