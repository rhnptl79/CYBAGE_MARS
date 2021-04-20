package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    EditText et_f_name, et_l_name, et_email, et_password, et_con_password;
    TextView tv_signup;
    Button btn_signup;
    boolean iStatus;
    FirebaseAuth firebaseAuth;
    RelativeLayout loading_view;
    ImageView add_image;
    ChoosePhotoHelper choosePhotoHelper;
    String imagePath = "";
    String pathImageFirebase = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et_f_name = findViewById(R.id.et_f_name);
        et_l_name = findViewById(R.id.et_l_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_con_password = findViewById(R.id.et_con_password);
        tv_signup = findViewById(R.id.tv_signup);
        btn_signup = findViewById(R.id.btn_signup);
        add_image = findViewById(R.id.add_image);
        loading_view = findViewById(R.id.loading_view);

    }
}