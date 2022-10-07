package com.example.lcmonitoring;

public class User  {
    String employeeID,name,mobileNumber,CUGNUmber,email,designation,password,confirmPassword;

    public User(){}

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

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCUGNUmber() {
        return CUGNUmber;
    }

    public void setCUGNUmber(String CUGNUmber) {
        this.CUGNUmber = CUGNUmber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
