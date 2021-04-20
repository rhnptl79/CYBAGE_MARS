package com.example.marsapp.data;

import java.util.ArrayList;

public class PurchasePrefData {
    String email;

    public void setCourseList(ArrayList<String> courseList) {
        this.courseList = courseList;
    }

    ArrayList<String> courseList;


    public PurchasePrefData(String email, ArrayList<String> courseList) {
        this.email = email;
        this.courseList = courseList;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getCourseList() {
        return courseList;
    }
}
