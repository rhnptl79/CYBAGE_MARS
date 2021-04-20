package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

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
        preferences = new MyPreferences(this);

        firebaseAuth = FirebaseAuth.getInstance();
        btn_signup.setOnClickListener(v -> {
            if (imagePath.equalsIgnoreCase("")) {
                showToast("Please select image!!");
                return;
            }
            if (Validation.isEmptyData(et_f_name)) {
                if (Validation.isEmptyData(et_l_name)) {
                    if (Validation.isEmptyData(et_email)) {
                        if (Validation.isEmptyData(et_password)) {
                            if (Validation.isEmptyData(et_con_password)) {
                                if (Validation.isValidEmail(et_email)) {
                                    if (Validation.isPasswordMatch(et_password, et_con_password, SignupActivity.this)) {
                                        storagePics();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        tv_signup.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        });

        add_image.setOnClickListener(v -> {
            choosePhotoHelper = ChoosePhotoHelper.with(SignupActivity.this)
                    .asFilePath()
                    .build(photo -> {
                        Log.d("Picc", photo);
                        Uri myUri = Uri.fromFile(new File(photo));
                        preferences.saveString(MyPreferences.USER_IMAGE,photo);
                        try {
                            Bitmap oribitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), myUri);
                            add_image.setImageBitmap(oribitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        Glide.with(SignupActivity.this)
//                                .load(photo)
//                                .into(add_image);
                        imagePath = photo;

//                        storePicss();
                    });

            choosePhotoHelper.showChooser();
        });

    }
}