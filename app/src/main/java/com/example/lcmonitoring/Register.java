package com.example.lcmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rey.material.widget.EditText;

public class Register extends AppCompatActivity {
TextView login_text;
Button register_button;
TextInputEditText register_employeeID,register_name,register_mobileNumber,register_CUGNumber,register_emailID,register_designation,register_createPassword,register_confirmPassword;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        String emailID = register_emailID.getText().toString().trim();
        String designation = register_designation.getText().toString().trim();
        String createPassword = register_createPassword.getText().toString().trim();
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
        }if(mobileNumber.length() < 10 || mobileNumber.length() > 10){
            register_mobileNumber.setError("Enter 10 Digit Number !");
            register_mobileNumber.requestFocus();
            return;
        }
        if(CUGNUmber.isEmpty()){
            register_CUGNumber.setError("CUG Number is Required !");
            register_CUGNumber.requestFocus();
            return;
        }if(CUGNUmber.length() < 10 || CUGNUmber.length() > 10){
            register_CUGNumber.setError("Enter 10 Digit Number !");
            register_CUGNumber.requestFocus();
            return;
        }
        if(emailID.isEmpty()){
            register_emailID.setError("Email ID is Required !");
            register_emailID.requestFocus();
            return;
        }if(!Patterns.EMAIL_ADDRESS.matcher(emailID).matches()){
            register_emailID.setError("Enter Proper Email ID !");
            register_emailID.requestFocus();
            return;
        }
        if(designation.isEmpty()){
            register_designation.setError("Designation is Required !");
            register_designation.requestFocus();
            return;
        } if(createPassword.isEmpty()){
            register_createPassword.setError("Password is Required !");
            register_createPassword.requestFocus();
            return;
        }
        if(createPassword.length() < 8 ||createPassword.length() > 15){
            register_createPassword.setError("Length is Between 8 to 15 !");
            register_createPassword.requestFocus();
            return;
        }
        if(confirmPassword.isEmpty()){
            register_confirmPassword.setError("Confirm Password is Required !");
            register_confirmPassword.requestFocus();
            return;
        }if(!createPassword.equals(confirmPassword)){
            register_confirmPassword.setError("Confirm Password is Same as Create Password !");
            register_confirmPassword.requestFocus();
            return;
        }
        else{
          startActivity(new Intent(Register.this, Login.class));
        }
    }
}