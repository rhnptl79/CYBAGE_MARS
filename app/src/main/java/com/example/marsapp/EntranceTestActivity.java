package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marsapp.data.EntranceTestData;

import java.util.ArrayList;

public class EntranceTestActivity extends AppCompatActivity {

    ArrayList<EntranceTestData> qusData;
    RecyclerView rv_question;
    AdapterEntranceTest adapter;
    Button btn_submit;
    TextView tv_time;
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance_test);
    }
}