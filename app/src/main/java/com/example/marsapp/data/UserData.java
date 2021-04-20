package com.example.marsapp.data;

import java.util.ArrayList;

public class UserData {
    String strImagePath;
    String firstName;
    String lastName;
    String email;
    String password;


    ArrayList<CourseData> courseList;
    public UserData(String strImagePath,String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.strImagePath=strImagePath;
    }
    public String getStrImagePath() {
        return strImagePath;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<CourseData> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<CourseData> courseList) {
        this.courseList = courseList;
    }

}
