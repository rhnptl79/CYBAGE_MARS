package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    TextView tv_signup;
    EditText et_name, et_password;
    FirebaseAuth firebaseAuth;
    RelativeLayout loading_view;
    boolean iStatus;
    CallbackManager callbackManager;

    LoginButton loginButton;
    GoogleSignInClient mGoogleSignInClient;
    MyPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = new MyPreferences(this);

        if (preferences.getBool(MyPreferences.IS_LOGIN)) {
            startCustomerActivity();
        }
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        tv_signup = findViewById(R.id.tv_signup);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        loading_view = findViewById(R.id.loading_view);
//Fb login
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email"));
        callbackManager = CallbackManager.Factory.create();



        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("getAccessToken", loginResult.getAccessToken().getToken());
                        getFbInfo(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.v("LoginActivity", "cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.v("LoginActivity", exception.getCause().toString());
                    }
                });

//Gmail LOgin

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        firebaseAuth = FirebaseAuth.getInstance();
        tv_signup.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
        btn_login.setOnClickListener(v -> {
            if (Validation.isEmptyData(et_name)) {
                if (Validation.isValidEmail(et_name)) {
                    if (Validation.isEmptyData(et_password)) {
                        signinUser(et_name.getText().toString());
                    }
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        releaseKeyHash();
    }






















}