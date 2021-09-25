package com.example.progym.user.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.progym.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScheduleUserView extends AppCompatActivity {

    TextView title,des,mon,tue,wed,thu,fri,sat,sun;
    DatabaseReference proGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_user_view);

        String key = getIntent().getStringExtra("Key");
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Schedule").child(key);

        title = findViewById(R.id.userViewScTitle);
        des = findViewById(R.id.userViewScDesc);
        mon = findViewById(R.id.userViewMon);
        tue = findViewById(R.id.userViewTue);
        wed = findViewById(R.id.userViewWed);
        thu = findViewById(R.id.userViewThu);
        fri = findViewById(R.id.userViewFri);
        sat = findViewById(R.id.userViewSat);
        sun = findViewById(R.id.userViewSun);

        title.setText(getIntent().getStringExtra("Title"));
        des.setText(getIntent().getStringExtra("Description"));
        mon.setText(getIntent().getStringExtra("Mon"));
        tue.setText(getIntent().getStringExtra("Tue"));
        wed.setText(getIntent().getStringExtra("Wed"));
        thu.setText(getIntent().getStringExtra("Thu"));
        fri.setText(getIntent().getStringExtra("Fri"));
        sat.setText(getIntent().getStringExtra("Sat"));
        sun.setText(getIntent().getStringExtra("Sun"));
    }
}