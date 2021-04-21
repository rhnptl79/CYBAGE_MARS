package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.marsapp.data.CourseContentData;
import com.example.marsapp.database.DatabaseHandler;

import java.util.ArrayList;

public class CourseVideoListActivity extends AppCompatActivity {

    RecyclerView rv_course_list;
    DatabaseHandler databaseHandler;
    ArrayList<CourseContentData> data;
    AdapterCourseVideo adapter;
    ImageView btn_back;
    Button btn_take_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_video_list);
    }
}