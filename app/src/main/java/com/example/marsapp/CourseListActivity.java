package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.marsapp.data.CourseData;
import com.example.marsapp.database.DatabaseHandler;
import com.example.marsapp.util.MyPreferences;

import java.util.ArrayList;

public class CourseListActivity extends AppCompatActivity {

    RecyclerView rv_course_list;
    AdapterCourseList adapter;
    ArrayList<CourseData> data;
    DatabaseHandler databaseHandler;
    TextView tv_logOUt;
    MyPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
    }
}