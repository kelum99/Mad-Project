package com.example.progym.admin.schedules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progym.R;
import com.example.progym.admin.exercises.Exercise;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSchedule extends AppCompatActivity {
        EditText title,scheduleID,description;
        EditText mon,tue,wed,thu,fri,sat,sun;
        Button addSchedule;
        DatabaseReference proGym;
        Schedule schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        title = findViewById(R.id.scheduleTitleTxt);
        scheduleID = findViewById(R.id.scheduleID);
        description = findViewById(R.id.scheduleDesTxt);
        mon = findViewById(R.id.monday);
        tue = findViewById(R.id.tuesday);
        wed = findViewById(R.id.wednesday);
        thu = findViewById(R.id.thursday);
        fri = findViewById(R.id.friday);
        sat = findViewById(R.id.saturday);
        sun = findViewById(R.id.sunday);

        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Schedule");

        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertExercise();
                clearFields();
            }
        });

    }
    private void insertExercise() {
        schedule = new Schedule();

        schedule.setScTitle(title.getText().toString().trim());
        schedule.setScKey(scheduleID.getText().toString().trim());
        schedule.setScDescription(description.getText().toString().trim());
        schedule.setMonday(mon.getText().toString().trim());
        schedule.setTuesday(tue.getText().toString().trim());
        schedule.setWednesday(wed.getText().toString().trim());
        schedule.setThursday(thu.getText().toString().trim());
        schedule.setFriday(fri.getText().toString().trim());
        schedule.setSaturday(sat.getText().toString().trim());
        schedule.setSunday(sun.getText().toString().trim());


        proGym.child(scheduleID.getText().toString().trim()).setValue(schedule);
        Toast.makeText(this, "Exercise Added!", Toast.LENGTH_SHORT).show();
    };
    private void clearFields() {
        title.setText("");
        scheduleID.setText("");
        description.setText("");
        mon.setText("");
        tue.setText("");
        wed.setText("");
        thu.setText("");
        fri.setText("");
        sat.setText("");
        sun.setText("");
    };
}