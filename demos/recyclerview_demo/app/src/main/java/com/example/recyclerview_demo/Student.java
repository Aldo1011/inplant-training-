package com.example.recyclerview_demo;

public class Student {

    private String studentName;
    private int studentId;

    public Student(){

        this.studentId=100;
        this.studentName="Student1";

    }

    public Student(int sId,String sName){

        this.studentId=sId;
        this.studentName=sName;


    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
