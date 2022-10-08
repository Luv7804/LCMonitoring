package com.example.lcmonitoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView login_text;
    Button register_button;
//    String verificationID;
    TextInputEditText register_employeeID,register_name,register_mobileNumber,register_CUGNumber,register_emailID,register_designation,register_createPassword,register_confirmPassword;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        login_text = findViewById(R.id.login_text);
        register_button = findViewById(R.id.register_button);
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_user();
            }
        });

    }

    public void register_user(){

        register_employeeID = findViewById(R.id.register_employeeID);
        register_name= findViewById(R.id.register_name);
        register_mobileNumber= findViewById(R.id.register_mobileNumber);
        register_CUGNumber= findViewById(R.id.register_CUGNumber);
        register_emailID= findViewById(R.id.register_emailID);
        register_designation= findViewById(R.id.register_designation);
        register_createPassword= findViewById(R.id.register_createPassword);
        register_confirmPassword= findViewById(R.id.register_confirmPassword);

        String employeeID = register_employeeID.getText().toString().trim();
        String name = register_name.getText().toString().trim();
        String mobileNumber = register_mobileNumber.getText().toString().trim();
        String CUGNUmber = register_CUGNumber.getText().toString().trim();
        String email = register_emailID.getText().toString().trim();
        String designation = register_designation.getText().toString().trim();
        String password = register_createPassword.getText().toString().trim();
        String confirmPassword = register_confirmPassword.getText().toString().trim();

        //        Validation Of Registration

        if(employeeID.isEmpty()){
            register_employeeID.setError("Employee ID is Required !");
            register_employeeID.requestFocus();
            return;
        }if(employeeID.length() < 8){
            register_employeeID.setError("Minimum Length is 8 Digit !");
            register_employeeID.requestFocus();
            return;
        }
        if(name.isEmpty()){
            register_name.setError("Employee Name is Required !");
            register_name.requestFocus();
            return;
        } if(mobileNumber.isEmpty()){
            register_mobileNumber.setError("Mobile Number is Required !");
            register_mobileNumber.requestFocus();
            return;
        }if(mobileNumber.length() != 10){
            register_mobileNumber.setError("Enter 10 Digit Number !");
            register_mobileNumber.requestFocus();
            return;
        }
        if(CUGNUmber.isEmpty()){
            register_CUGNumber.setError("CUG Number is Required !");
            register_CUGNumber.requestFocus();
            return;
        }if(CUGNUmber.length() != 10){
            register_CUGNumber.setError("Enter 10 Digit Number !");
            register_CUGNumber.requestFocus();
            return;
        }
        if(email.isEmpty()){
            register_emailID.setError("Email ID is Required !");
            register_emailID.requestFocus();
            return;
        }if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            register_emailID.setError("Enter Proper Email ID !");
            register_emailID.requestFocus();
            return;
        }
        if(designation.isEmpty()){
            register_designation.setError("Designation is Required !");
            register_designation.requestFocus();
            return;
        } if(password.isEmpty()){
            register_createPassword.setError("Password is Required !");
            register_createPassword.requestFocus();
            return;
        }
        if(password.length() < 8 ||password.length() > 15){
            register_createPassword.setError("Length is Between 8 to 15 !");
            register_createPassword.requestFocus();
            return;
        }
        if(confirmPassword.isEmpty()){
            register_confirmPassword.setError("Confirm Password is Required !");
            register_confirmPassword.requestFocus();
            return;
        }if(!password.equals(confirmPassword)){
            register_confirmPassword.setError("Confirm Password is Same as Create Password !");
            register_confirmPassword.requestFocus();
            return;
        }
        View progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("User");

                    User user = new User(employeeID,name,mobileNumber,CUGNUmber,email,designation,password,confirmPassword);
//                   DatabaseReference realTimeData = database.getReference("User").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
//                  myRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>()
                    FirebaseDatabase.getInstance().getReference("User").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>(){

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
//                                        sendverificationcode(CUGNUmber);
                                        Toast.makeText(Register.this, "Registerd successfully", Toast.LENGTH_LONG).show();;
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(Register.this, Login.class);
//                                        intent.putExtra("CUGNumber",CUGNUmber);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }else{
                    Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();;
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
//    private void sendverificationcode(String CUGNUmber) {
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber("+91"+CUGNUmber)       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(this)                 // Activity (for callback binding)
//                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//        @Override
//        public void onVerificationCompleted(PhoneAuthCredential credential) {
//            final String code = credential.getSmsCode();
//            if(code != null){
//                verifyCode(code);
//            }
//        }
//
//        @Override
//        public void onVerificationFailed(@NonNull FirebaseException e) {
//            Toast.makeText(Register.this, "Verification Failed", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onCodeSent(@NonNull String s,
//                @NonNull PhoneAuthProvider.ForceResendingToken token) {
//            super.onCodeSent(s,token);
//            verificationID = s;
//
//        }
//    };
//    private void  verifyCode(String code){
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,code);
//        signinbyCredentials(credential);
//    }
//
//    private void signinbyCredentials(PhoneAuthCredential credential) {
//
//    }
}