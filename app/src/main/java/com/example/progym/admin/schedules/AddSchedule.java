package com.example.progym.admin.schedules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.progym.R;

public class AddSchedule extends AppCompatActivity {
        EditText title,subTitle,description;
        EditText mon,tue,wed,thu,fir,sat,sun;
        Button addSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        title = findViewById(R.id.scheduleTitleTxt);
        subTitle = findViewById(R.id.scheduleSTitleTxt);
        description = findViewById(R.id.scheduleDesTxt);
    }
}