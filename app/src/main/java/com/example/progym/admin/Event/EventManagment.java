package com.example.progym.admin.Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.progym.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EventManagment extends AppCompatActivity {

    Button addEventBtn;
    ListView eventList;
    DatabaseReference proGym;

    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_managment);

        Query proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Event");
        eventList = findViewById(R.id.eventLV);
        addEventBtn = findViewById(R.id.add_event);

        FirebaseListOptions<Event> options = new FirebaseListOptions.Builder<Event>()
                .setLayout(R.layout.eventlist)
                .setQuery(proGym, Event.class)
                .build();
        adapter = new FirebaseListAdapter(options) {

            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView Type = v.findViewById(R.id.ev_Type);
                TextView key = v.findViewById(R.id.ev_ID);
                Event event = (Event) model;
                Type.setText(event.getEventType());
                key.setText(event.getEventID());

            }
        };
        eventList.setAdapter(adapter);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateEvent.class);
                startActivity(intent);
            }
        });

        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent updateDelete = new Intent(getApplicationContext(), UpdateAndDelete.class);
                Event ev = (Event) parent.getItemAtPosition(position);
                updateDelete.putExtra("Type", ev.getEventType());
                updateDelete.putExtra("Key", ev.getEventID());
                updateDelete.putExtra("Description", ev.getEventDescription());
                updateDelete.putExtra("Date", ev.getEventDate());
                updateDelete.putExtra("Time", ev.getEventTime());

                startActivity(updateDelete);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
