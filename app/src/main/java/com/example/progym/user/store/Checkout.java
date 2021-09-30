package com.example.progym.user.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.progym.R;

public class Checkout extends AppCompatActivity {

    Button checkoutBtn,pay_btn;
    TextView price,title,user_name,address,mobile,mail,pay_amount,shipping_amount;

    float item_price,tot,shipping_fee = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        price = findViewById(R.id.checkoutPrice);
        title = findViewById(R.id.checkoutTitle);

        user_name = findViewById(R.id.c_user_name);
        address = findViewById(R.id.c_address);
        mobile = findViewById(R.id.c_mobile);
        mail = findViewById(R.id.c_mail);

        pay_amount = findViewById(R.id.pay_amount);
        shipping_amount = findViewById(R.id.shipping_fee);

        price.setText(getIntent().getStringExtra("Price"));
        title.setText(getIntent().getStringExtra("Title"));

        user_name.setText(getIntent().getStringExtra("u_name"));
        address.setText(getIntent().getStringExtra("u_address"));
        mobile.setText(getIntent().getStringExtra("u_mobile"));
        mail.setText(getIntent().getStringExtra("u_mail"));

        item_price = Float.parseFloat(getIntent().getStringExtra("Price"));

        tot = item_price + shipping_fee;
        pay_amount.setText(String.valueOf(tot));
        shipping_amount.setText(String.valueOf(shipping_fee));


    }
}