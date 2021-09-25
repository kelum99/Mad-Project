package com.example.progym.user.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.progym.R;
import com.example.progym.admin.schedules.Schedule;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {


    View view;
    DatabaseReference proGym;
    ListView scheduleList;
    ArrayList<String> scheduleAL;
    FirebaseListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.schedule_fragment, container, false);

        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Schedule");
        scheduleList = view.findViewById(R.id.scheduleLV);
        scheduleAL = new ArrayList<>();

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

        scheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),ScheduleUserView.class);
                Schedule schedule = (Schedule) parent.getItemAtPosition(position);

                intent.putExtra("Title",schedule.getScTitle());
                intent.putExtra("Description", schedule.getScDescription());
                intent.putExtra("Mon", schedule.getMonday());
                intent.putExtra("Tue", schedule.getTuesday());
                intent.putExtra("Wed", schedule.getWednesday());
                intent.putExtra("Thu", schedule.getThursday());
                intent.putExtra("Fri", schedule.getFriday());
                intent.putExtra("Sat", schedule.getSaturday());
                intent.putExtra("Sun", schedule.getSunday());
                intent.putExtra("Key",schedule.getScKey());

                startActivity(intent);


            }
        });

        return view;
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