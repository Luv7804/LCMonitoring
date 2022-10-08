package com.example.lcmonitoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPVerify extends AppCompatActivity {
    private TextInputEditText otpNumber;
    private Button submitBtn;
    private TextView successMsg;
    String CUGNumber;
    String otpValue;
    String verificationID;
    String code;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);
        otpNumber = findViewById(R.id.otp_number);
        submitBtn = findViewById(R.id.verify_button);
//        successMsg = findViewById(R.id.successMsg);
        verificationID = otpNumber.getText().toString();
        Intent intent = getIntent();
        CUGNumber = intent.getStringExtra("CUGNumber");

        otpNumber.setVisibility(View.VISIBLE);
        submitBtn.setVisibility(View.VISIBLE);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkOtpNumber = TextUtils.isEmpty(verificationID);
                if(code != null){
                    verifyCode(code);
                }
            }
        });


    }

    private void sendverificationcode(String CUGNumber) {

        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                 code = credential.getSmsCode();
//                if(code != null){
//                    verifyCode(code);
//                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(OTPVerify.this, "Verification Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(s,token);
                verificationID = s;

            }
        };

        PhoneAuthOptions options =
        PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber("+91"+CUGNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,code);
        signinbyCredentials(credential);
    }

    private void signinbyCredentials(PhoneAuthCredential credential) {

    }

}