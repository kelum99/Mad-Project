package com.example.progym.user.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.progym.R;
import com.example.progym.user.payment.MyPayment;

import java.text.DecimalFormat;

public class Checkout extends AppCompatActivity {

    Button checkoutBtn,payBtn;
    TextView price,title,user_name,address,mobile,mail,pay_amount,shipping_amount;

    double item_price,tot,shipping_fee = 200.0;
    DecimalFormat decim = new DecimalFormat("#.##");
    Double shippingFee = Double.parseDouble(decim.format(shipping_fee));

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

        payBtn = findViewById(R.id.pay_btn);

        pay_amount = findViewById(R.id.pay_amount);
        shipping_amount = findViewById(R.id.shipping_fee);

        price.setText(getIntent().getStringExtra("Price"));
        title.setText(getIntent().getStringExtra("Title"));

        user_name.setText(getIntent().getStringExtra("u_name"));
        address.setText(getIntent().getStringExtra("u_address"));
        mobile.setText(getIntent().getStringExtra("u_mobile"));
        mail.setText(getIntent().getStringExtra("u_mail"));

        item_price = Double.parseDouble(getIntent().getStringExtra("Price"));




        tot = item_price + shippingFee;
        pay_amount.setText(String.valueOf(tot));
        shipping_amount.setText(String.valueOf(shippingFee));

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), MyPayment.class);
                in.putExtra("totalPayment", new Double(tot).toString());

                startActivity(in);
            }
        });

    }
}