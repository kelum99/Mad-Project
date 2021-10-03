package com.example.progym.user.store;


public class ItemCalculations {

    int itemCount = 0;
    float itemPrice = 0;

    protected int calculateCartItems(){

        return itemCount + 1;
    }

    protected float calculateCartPrice(float price){
        return itemPrice + price;
    }
}
