package com.example.progym.user.Diet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.progym.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DietUserView extends AppCompatActivity {

    DatabaseReference proGym;
    TextView title,des,breakfast,lunch,dinner,snacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_user_view);

        String key = getIntent().getStringExtra("Key");
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Diet").child(key);

        title = findViewById(R.id.userViewDietTitle);
        des = findViewById(R.id.userViewDietDesc);
        breakfast = findViewById(R.id.userViewBF);
        lunch = findViewById(R.id.userViewLunch);
        dinner = findViewById(R.id.userViewDinner);
        snacks = findViewById(R.id.userViewSnacks);

        title.setText(getIntent().getStringExtra("Title"));
        des.setText(getIntent().getStringExtra("Des"));
        breakfast.setText(getIntent().getStringExtra("Breakfast"));
        lunch.setText(getIntent().getStringExtra("Lunch"));
        dinner.setText(getIntent().getStringExtra("Dinner"));
        snacks.setText(getIntent().getStringExtra("Snacks"));

    }
}