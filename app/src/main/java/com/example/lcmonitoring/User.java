package com.example.lcmonitoring;

public class User {
    String employeeID,name,mobileNumber,CUGNUmber,email,designation,password,confirmPassword;
    public User(){

    }
    public User(String employeeID,String name,String mobileNumber,String CUGNUmber,String emailID,String designation,String createPassword,String confirmPassword){
        this.employeeID = employeeID;
        this.name=name;
        this.mobileNumber = mobileNumber;
        this.CUGNUmber = CUGNUmber;
        this.email =emailID;
        this.designation = designation;
        this.password = createPassword;
        this.confirmPassword = confirmPassword;
    }

}
