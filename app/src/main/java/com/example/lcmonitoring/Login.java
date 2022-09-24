package com.example.lcmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    Button login_button;
    TextView register_text;
    TextInputEditText login_employeeID,login_createPassword;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register_text =findViewById(R.id.register_text);
        login_employeeID = findViewById(R.id.login_employeeID);
        login_button = findViewById(R.id.login_button);
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
        else{
            startActivity(new Intent(Login.this,Register.class));
        }
    }
}

