package com.example.marsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityPayment extends AppCompatActivity implements PaymentMethodNonceCreatedListener,
        BraintreeCancelListener, BraintreeErrorListener, DropInResult.DropInResultListener{

    private static final int DROP_IN_REQUEST = 100;

    private static final String KEY_NONCE = "nonce";
    TextView tv_course_name, tv_course_id, tv_fee, tv_total_amout;
    DatabaseHandler databaseHandler;
    String mAuthorization = "sandbox_tvd88k39_qcy7k7y59njv599v";
    MyPreferences preferences;
    ArrayList<PurchasePrefData> prefList;
    private PaymentMethodType mPaymentMethodType;
    private PaymentMethodNonce mNonce;
    private CardView mPaymentMethod;
    //        private TextView mNonceString;
//        private TextView mNonceDetails;
//        private TextView mDeviceData;
    private ImageView mPaymentMethodIcon;
    private TextView mPaymentMethodTitle;
    private TextView mPaymentMethodDescription;
    private Button mAddPaymentMethodButton;
    private Button mPurchaseButton;
    private ProgressDialog mLoading;
    private boolean mShouldMakePurchase = false;
    private boolean mPurchased = false;
    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }
}