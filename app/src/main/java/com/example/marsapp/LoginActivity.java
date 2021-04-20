package com.example.marsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

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


    private void startCustomerActivity() {
        Intent intent = new Intent(LoginActivity.this, CourseListActivity.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 102);
    }

    private void getFbInfo(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            Log.d("LOG_TAG", "fb json object: " + object);
                            Log.d("LOG_TAG", "fb graph response: " + response);

                            String id = object.getString("id");
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String image_url = "http://graph.facebook.com/" + id + "/picture?type=large";

                            preferences.saveString(MyPreferences.USER_NAME,first_name+ " "+last_name);
                            String email;
                            if (object.has("email")) {
                                email = object.getString("email");
                            }
                            addCourse();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,email,gender,birthday"); // id,first_name,last_name,email,gender,birthday,cover,picture.type(large)
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            preferences.saveString(MyPreferences.USER_NAME,account.getDisplayName());

            addCourse();
            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }

    private void signinUser(String child) {
        loading_view.setVisibility(View.VISIBLE);
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        ArrayList<UserData> userList = new ArrayList<>();
        iStatus = false;

        firebaseDatabase.child(Constants.DB_USER_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    HashMap<String, String> users = new HashMap();
                    users = (HashMap<String, String>) snap.getValue();
                    UserData userData = new UserData(users.get("strImagePath"), users.get("firstName"), users.get("lastName"), users.get("email"), users.get("password"));

                    Gson gson = new Gson();
                    String s=gson.toJson(users.get("purchasedCourse"));
                    Type type = new TypeToken<ArrayList<CourseData>>() {}.getType();
                    ArrayList<CourseData> arrayList = gson.fromJson(s, type);
                    userData.setCourseList(arrayList);

                    userList.add(userData);
                    if (userData.getEmail() != null) {
                        if (userData.getEmail().equals(et_name.getText().toString()) && userData.getPassword().equals(et_password.getText().toString())) {
                            iStatus = true;
                            preferences.saveString(MyPreferences.USER_NAME,userData.getFirstName()+" "+userData.getLastName());
                            preferences.saveString(MyPreferences.Email,userData.getEmail());
                            preferences.saveString(MyPreferences.LOGIN_USER_DATA,new Gson().toJson(userData));
                        }
                    }
                }
                if (iStatus) {
                    showToast("Login successfully!");

                    addCourse();

                } else {
                    showToast("Email or Password is incorrect");
                }

                loading_view.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                loading_view.setVisibility(View.GONE);
            }
        });
    }

    private void addCourse() {
        DatabaseHandler handler = new DatabaseHandler(this);
        handler.addCourse(new CourseData(1, "CSS", true, "$90"));
        handler.addCourse(new CourseData(2, "HTML", true, "$200"));
        handler.addCourse(new CourseData(3, "JAVA", true, "$50"));
        handler.addCourse(new CourseData(4, "JAVA SCRIPT", true, "$250"));
        handler.addCourse(new CourseData(5, "MY SQL DATABASE", true, "$150"));
        handler.addCourse(new CourseData(6, "PHP", false, "Free"));
//        startActivity(new Intent(LoginActivity.this,CourseListActivity.class));
        addCourseVideo();
    }



















}