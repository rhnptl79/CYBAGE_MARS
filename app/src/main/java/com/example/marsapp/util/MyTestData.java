package com.example.marsapp.util;

import com.example.marsapp.data.EntranceTestData;

import java.util.ArrayList;

public class MyTestData {

    public static ArrayList<EntranceTestData> getFinalTestData(int type) {
        switch (type) {
            case 1:
                return getCssFinalQuestion();
            case 2:
                return getHtmlFinalQuestion();
            case 3:
                return getJavaFinalQuestion();
            case 4:
                return getJavaScriptFinalQuestion();
            case 5:
                return getMySqlFinalQuestion();
            case 6:
                return getPhpFinalQuestion();
        }
        return null;
    }
    public static ArrayList<EntranceTestData> getEntranceData(int type) {
        switch (type) {
            case 1:
                return getCssEntranceQuestion();
            case 2:
                return getHtmlEntranceQuestion();
            case 3:
                return getJavaEntranceQuestion();
            case 4:
                return getJavaScriptEntranceQuestion();

            case 5:
                return getMySqlEntranceQuestion();
        }
        return null;
    }

    private static ArrayList<EntranceTestData> getCssEntranceQuestion() {
        ArrayList<EntranceTestData> list = new ArrayList<>();
        ArrayList<String> q1Option = new ArrayList<>();
        q1Option.add("Cascade style sheets");
        q1Option.add("Color and style sheets");
        q1Option.add("Cascading style sheets");
        q1Option.add("All of the above");

        ArrayList<String> q2Option = new ArrayList<>();
        q2Option.add("bgcolor");
        q2Option.add("color");
        q2Option.add("background-color");
        q2Option.add("All of the above");


        list.add(new EntranceTestData("CSS stands for?", "A", "", q1Option));
        list.add(new EntranceTestData("The property in CSS used to change the text color of an element is", "B", "", q2Option));
        return list;
    }

    private static ArrayList<EntranceTestData> getHtmlEntranceQuestion() {
        ArrayList<EntranceTestData> list = new ArrayList<>();
        ArrayList<String> q1Option = new ArrayList<>();
        q1Option.add("HighText Machine Language");
        q1Option.add("HyperText and links Markup Language");
        q1Option.add("HyperTextMarkup Language");
        q1Option.add("None of these");

        ArrayList<String> q2Option = new ArrayList<>();
        q2Option.add("<pre>");
        q2Option.add("<a>");
        q2Option.add("<b>");
        q2Option.add("<br>");


        list.add(new EntranceTestData("HTML stands for?", "C", "", q1Option));
        list.add(new EntranceTestData("Which of the following element is responsible for making the text bold in HTML?", "C", "", q2Option));
        return list;
    }

    private static ArrayList<EntranceTestData> getJavaEntranceQuestion() {
        ArrayList<EntranceTestData> list = new ArrayList<>();
        ArrayList<String> q1Option = new ArrayList<>();
        q1Option.add("0");
        q1Option.add("Not a Number");
        q1Option.add("Infinity");
        q1Option.add("Run time exception");

        ArrayList<String> q2Option = new ArrayList<>();
        q2Option.add("for ( inti = 99; i>= 0; i / 9 )");
        q2Option.add("for ( inti = 7; i<= 77; i += 7 )");
        q2Option.add("for ( inti = 20; i>= 2; - -i )");
        q2Option.add("for ( inti = 2; i<= 20; i = 2* i )");


        list.add(new EntranceTestData("What does the expression float a = 35 / 0 return?", "C", "", q1Option));
        list.add(new EntranceTestData("Which of the following for loop declaration is not valid?", "A", "", q2Option));
        return list;
    }


}
