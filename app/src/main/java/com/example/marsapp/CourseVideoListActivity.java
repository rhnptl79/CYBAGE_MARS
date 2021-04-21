package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        rv_course_list=findViewById(R.id.rv_course_list);
        btn_back=findViewById(R.id.btn_back);
        btn_take_test=findViewById(R.id.btn_take_test);

        data=new ArrayList<>();
        initView(data);
        databaseHandler=new DatabaseHandler(this);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_take_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CourseVideoListActivity.this,EntranceTestActivity.class);
                intent.putExtra("from","videoActivity");
                intent.putExtra("id", getIntent().getIntExtra("id",1));
                intent.putExtra("course", getIntent().getStringExtra("name"));
                startActivity(intent);


            }
        });
    }
}