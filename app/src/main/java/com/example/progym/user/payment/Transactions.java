package com.example.progym.user.payment;

import java.sql.Time;
import java.util.Date;

public class Transactions {
    private String amount;
    private String date;
    private String time;

    public Transactions() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
