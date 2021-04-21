package com.example.marsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        rv_question = findViewById(R.id.rv_question);
        btn_submit = findViewById(R.id.btn_submit);
        tv_time = findViewById(R.id.tv_time);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getIntent().hasExtra("from")) {
            qusData = MyTestData.getFinalTestData(getIntent().getIntExtra("id", 1));
        } else {
            qusData = MyTestData.getEntranceData(getIntent().getIntExtra("id", 1));
        }

        initView();
        startTimer(60);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<EntranceTestData> data = adapter.getData();
                int totatlQuestion = data.size();
                int correctAns = 0;
                for (int i = 0; i < totatlQuestion; i++) {
                    if (data.get(i).getAnswer().equalsIgnoreCase(data.get(i).getUserSubmitAnswer())) {
                        correctAns += 1;
                    }
                }
                int totalPercentageObtain = 0;
                totalPercentageObtain = (correctAns / totatlQuestion) * 100;

                if (totalPercentageObtain >= 40) {
                    isShowDialog(true, "You are pass!!");

                } else {
                    isShowDialog(false, "Sorry you failed!! please try again.");
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//
        Toast.makeText(EntranceTestActivity.this, "You can't go back!", Toast.LENGTH_SHORT).show();
    }

    private void isShowDialog(boolean isPass, String message) {
        AlertDialog.Builder alertDialog= new AlertDialog.Builder(EntranceTestActivity.this)
                .setMessage(message).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isPass) {
                            if (!getIntent().hasExtra("from")) {
                                Intent intent = new Intent(EntranceTestActivity.this, ActivityPayment.class);
                                intent.putExtra("id", getIntent().getIntExtra("id", 1));
                                intent.putExtra("name", getIntent().getStringExtra("name"));
                                intent.putExtra("fees", getIntent().getStringExtra("fees"));
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(EntranceTestActivity.this, ActivityCertificate.class);
                                intent.putExtra("course", getIntent().getStringExtra("course"));
                                startActivity(intent);
                            }
                        }
                        finish();
                    }
                });

        try {
            alertDialog.show();
        }
        catch (WindowManager.BadTokenException e) {
            Log.d("ErrorMessage:",e.getMessage());
        }
    }
}