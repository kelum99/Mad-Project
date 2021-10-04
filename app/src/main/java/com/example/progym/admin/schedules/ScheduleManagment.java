package com.example.progym.admin.schedules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.progym.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ScheduleManagment extends AppCompatActivity {

    Button addSchedule;
    DatabaseReference proGym;
    ListView scheduleList;
    ArrayList<String> scheduleAL;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_managment);

        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Schedule");
        scheduleList = findViewById(R.id.adminScheduleLV);
        scheduleAL = new ArrayList<>();
        addSchedule = findViewById(R.id.addScheduleBtn);

        FirebaseListOptions<Schedule> options = new FirebaseListOptions.Builder<Schedule>()
                .setLayout(R.layout.schedule_lv)
                .setQuery(proGym,Schedule.class)
                .build();
        adapter = new FirebaseListAdapter(options) {

            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView title = v.findViewById(R.id.UserScheduleTitle);
                TextView subTile = v.findViewById(R.id.UserScheduleSTitle);
                Schedule schedule = (Schedule) model;
                title.setText(schedule.getScTitle());
                subTile.setText(schedule.getScKey());

            }
        };
        scheduleList.setAdapter(adapter);

        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddSchedule.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}