package com.example.progym.user.payment;

import java.sql.Time;
import java.util.Date;

public class Transactions {
    private String amount;
    private String dateTime;


    public Transactions() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String toString(){
        return getAmount()+"-"+getDateTime();
    }
}
