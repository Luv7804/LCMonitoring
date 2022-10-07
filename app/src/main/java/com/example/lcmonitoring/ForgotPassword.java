package com.example.lcmonitoring;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgotPassword extends AppCompatActivity {
    TextInputEditText forgot_emailID;
    Button button;
    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgot_emailID = findViewById(R.id.forgot_emailID);
        mAuth = FirebaseAuth.getInstance();
        button = findViewById(R.id.forgot_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPassword();
            }
        });

    }
        public void forgotPassword () {
            String email = Objects.requireNonNull(forgot_emailID.getText()).toString().trim();

            if (email.isEmpty()) {
                forgot_emailID.setError("Email Address is required!");
                forgot_emailID.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                forgot_emailID.setError("please provide valid Email!");
                forgot_emailID.requestFocus();
                return;
            }
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override

                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ForgotPassword.this,"Check you Email Address",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ForgotPassword.this,"Somthing Went Wrong",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }





//            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        Toast.makeText(ForgotPassword.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
////                        forgotProgressbar.setVisibility(View.GONE);
////                        startActivity(new Intent(ForgotPassword.this, Login.class));
//                    }
//                    else {
//                        Toast.makeText(ForgotPassword.this, "Try again! something went wrong happend!", Toast.LENGTH_SHORT).show();
////                        forgotProgressbar.setVisibility(View.GONE);
//                    }
//                }
//            });
//
//        }
}
