package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityFaceRecognition extends AppCompatActivity {

    protected Interpreter tflite;
    private  int imageSizeX;
    private  int imageSizeY;

    private static final float IMAGE_MEAN = 0.0f;
    private static final float IMAGE_STD = 1.0f;

    public Bitmap oribitmap,testbitmap;
    public static Bitmap cropped;
    Uri imageuri;

    ImageView oriImage,testImage;
    Button buverify;
    TextView result_text;

    float[][] ori_embedding = new float[1][128];
    float[][] test_embedding = new float[1][128];


    ChoosePhotoHelper choosePhotoHelper;
    MyPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognition);
        preferences = new MyPreferences(this);
        initComponents();
    }
    private void initComponents() {

        oriImage=(ImageView)findViewById(R.id.image1);
        testImage=(ImageView)findViewById(R.id.image2);
        buverify=(Button)findViewById(R.id.verify);
        result_text=(TextView)findViewById(R.id.result);
//        Uri myUri = Uri.fromFile(new File(preferences.getString(MyPreferences.USER_IMAGE)));
//        try {
//            Bitmap oribitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), myUri);
//            oriImage.setImageBitmap(oribitmap);
//
//            face_detector(oribitmap,"original");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try{
            tflite=new Interpreter(loadmodelfile(this));
        }catch (Exception e) {
            e.printStackTrace();
        }

        oriImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),12);
            }
        });

        testImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),13);

            }
        });

        buverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double distance=calculate_distance(ori_embedding,test_embedding);

                if(distance<6.0)
                    result_text.setText("Result : Same Faces");
                else
                    result_text.setText("Result : Different Faces");

            }

        });

    }
}