package com.example.internshipproject_codestreak.viewmodel;

import java.util.List;

public class Challenge {

    public enum ChallengeType {
        MULTIPLE_CHOICE,
        CODE_OUTPUT,
        CODE_FIX,
        CODE_WRITE
    }

    private int id;
    private ChallengeType type;
    private String question;
    private String code;
    private List<String> options;
    private String expectedAnswer;
    private String explanation;

    public Challenge() {
        // Required for Firebase
    }

    public Challenge(
            int id,
            ChallengeType type,
            String question,
            String code,
            List<String> options,
            String expectedAnswer,
            String explanation
    ) {
        this.id = id;
        this.type = type;
        this.question = question;
        this.code = code;
        this.options = options;
        this.expectedAnswer = expectedAnswer;
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public ChallengeType getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getCode() {
        return code;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public String getExplanation() {
        return explanation;
    }


}
