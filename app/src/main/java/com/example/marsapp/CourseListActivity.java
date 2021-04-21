package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.marsapp.data.CourseData;
import com.example.marsapp.database.DatabaseHandler;
import com.example.marsapp.util.MyPreferences;

import java.lang.reflect.Type;
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
        rv_course_list = findViewById(R.id.rv_course_list);
        tv_logOUt = findViewById(R.id.tv_logOUt);

        databaseHandler = new DatabaseHandler(this);
        preferences = new MyPreferences(this);

        getUserData();

        data = new ArrayList<>();
        initView(data);

        getUserData();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            // REQUEST_CODE_LOCATION should be defined on your app level
            ActivityCompat.requestPermissions(this, permissions, 101);
        }

        tv_logOUt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLogout("Are you sure you want to logout?");
            }
        });
    }
    private void getUserData() {
        Gson gson = new Gson();

        String preData = preferences.getString(MyPreferences.LOGIN_USER_DATA);
        Type type = new TypeToken<UserData>() {
        }.getType();
        UserData userData = gson.fromJson(preData, type);
        if (userData.getCourseList() != null) {
            for (int i = 0; i < userData.getCourseList().size(); i++) {
                databaseHandler.updateCourseData(userData.getCourseList().get(i));
            }
        }

        data = new ArrayList<>();
        data.addAll(databaseHandler.getAllCourse());
        initView(data);
    }
}