package com.example.lcmonitoring;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button login_button;
    TextView register_text,forgot_text;
    TextInputEditText login_employeeID,login_createPassword;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        register_text =findViewById(R.id.register_text);
        login_employeeID = findViewById(R.id.login_employeeID);
        login_button = findViewById(R.id.login_button);
        forgot_text = findViewById(R.id.forgot_text);
        forgot_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,ForgotPassword.class));
            }
        });
        register_text.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(Login.this,Register.class));
    }
});
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });
    }

    public void login(){
        login_employeeID = findViewById(R.id.login_employeeID);
        String employeeID = login_employeeID.getText().toString().trim();
        login_createPassword= findViewById(R.id.login_createPassword);
        String createPassword = login_createPassword.getText().toString().trim();
        if(employeeID.isEmpty()){
            login_employeeID.setError("Employee ID is Required !");
            login_employeeID.requestFocus();
            return;
        }if(employeeID.length() < 8){
            login_employeeID.setError("Minimum Length is 8 Digit !");
            login_employeeID.requestFocus();
            return;
        }if(createPassword.isEmpty()){
            login_createPassword.setError("Password is Required !");
            login_createPassword.requestFocus();
            return;
        }
        if(createPassword.length() < 8 ||createPassword.length() > 15){
            login_createPassword.setError("Length is Between 8 to 15 !");
            login_createPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(employeeID, createPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(Login.this,Home.class));
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
}

