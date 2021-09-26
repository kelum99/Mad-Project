package com.example.progym.user.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.progym.R;

public class Checkout extends AppCompatActivity {

    Button checkoutBtn;
    TextView price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        price = findViewById(R.id.checkoutPrice);

        price.setText(getIntent().getStringExtra("Price"));

    }
}