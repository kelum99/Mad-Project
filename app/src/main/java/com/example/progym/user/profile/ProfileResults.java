package com.example.progym.user.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.progym.Home;
import com.example.progym.R;

public class ProfileResults extends AppCompatActivity {

    TextView tv_BMI, tv_bmr, tv_cals;
    TextView tv_h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_results);


        tv_BMI = findViewById(R.id.tv_BMI);
        tv_bmr = findViewById(R.id.tv_bmr);
        tv_cals = findViewById(R.id.tv_cals);

        Intent intent = getIntent();
        Float BMI = intent.getFloatExtra("BMI",0);
        String BMR = intent.getStringExtra("BMR");
        String Cals = intent.getStringExtra("Cal");


        tv_BMI.setText(String.valueOf(BMI));
        tv_bmr.setText(BMR);
        tv_cals.setText(Cals);

        TextView msg = findViewById(R.id.tv_msg);
        Analysis analysis = new Analysis();
        msg.setText(analysis.getAnalysis(BMI));

        CardView cv_analysis = findViewById(R.id.cv_analysis);
        msg.setBackgroundColor(analysis.getColor(BMI));

        Button btn_ok = findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProfileResults.this, Home.class);
                startActivity(intent1);
            }
        });



    }
}