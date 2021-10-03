package com.example.progym.user.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.progym.R;

public class Cart extends AppCompatActivity {

    TextView subTotal;
    TextView itemPrice;
    CheckBox itemCheck;
    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        subTotal = findViewById(R.id.display_subtot);
        itemPrice = findViewById(R.id.item_price);
        itemCheck = findViewById(R.id.cart_checkBoxt);
        btnCheckout = findViewById(R.id.go_checkout_btn);

        Intent intent = getIntent();
    }
}