package com.example.internshipproject_codestreak.viewmodel;

public class TestCase {

    private String input;
    private String expectedOutput;

    public TestCase() {
        //for Firebase
    }

    public TestCase(
            String input,
            String expectedOutput
    ) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    public String getInput() {
        return input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

}
