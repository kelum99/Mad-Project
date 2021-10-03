package com.example.progym.user.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progym.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EventUserView extends AppCompatActivity {

    TextView title, description, date, time;
    ImageView img;

    DatabaseReference proGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_user_view);

        String key = getIntent().getStringExtra("Key");
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Event").child(key);

        title = findViewById(R.id.userViewEvTitle);
        description = findViewById(R.id.UserViewEvDes);
        date = findViewById(R.id.userViewEvTitle2);
        time = findViewById(R.id.userViewEvTitle3);
        img = findViewById(R.id.imageView8);

        title.setText(getIntent().getStringExtra("Title"));
        description.setText(getIntent().getStringExtra("Description"));
        date.setText(getIntent().getStringExtra("Date"));
        time.setText(getIntent().getStringExtra("Time"));


        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(getIntent().getStringExtra("ImgUrl")).into(img);
    }

}