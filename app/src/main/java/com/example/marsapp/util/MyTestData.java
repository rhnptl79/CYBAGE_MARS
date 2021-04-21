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


}
