package com.example.lcmonitoring;

public class Taker
{
    String substationName,substationFeeder,Reason,otherDetail,CUGNUmber;

    public Taker(){

    }
    public Taker(String substationName,String substationFeeder,String Reason,String otherDetail,String CUGNUmber){
        this.substationName = substationName;
        this.substationFeeder = substationFeeder;
        this.Reason = Reason;
        this.otherDetail = otherDetail;
        this.CUGNUmber = CUGNUmber;
    }

    public String getSubstationName() {
        return substationName;
    }

    public void setSubstationName(String substationName) {
        this.substationName = substationName;
    }

    public String getSubstationFeeder() {
        return substationFeeder;
    }

    public void setSubstationFeeder(String substationFeeder) {
        this.substationFeeder = substationFeeder;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getOtherDetail() {
        return otherDetail;
    }

    public void setOtherDetail(String otherDetail) {
        this.otherDetail = otherDetail;
    }

    public String getCUGNUmber() {
        return CUGNUmber;
    }

    public void setCUGNUmber(String CUGNUmber) {
        this.CUGNUmber = CUGNUmber;
    }
}
