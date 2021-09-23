package com.example.progym.admin.exercises;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.progym.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDeleteExercise extends AppCompatActivity {
        EditText title, subTitle, description;
        Button updateBtn;
        Button deleteBtn;
        DatabaseReference proGym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_exercise);

        String key = getIntent().getStringExtra("Key").toString();
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Exercises").child(key);
        title = findViewById(R.id.updateExerciseTitleTxt);
        subTitle = findViewById(R.id.UpdateExerciseSitleTxt);
        description = findViewById(R.id.UpdateExerciseDesTxt);


        title.setText(getIntent().getStringExtra("Title"));
        subTitle.setText(getIntent().getStringExtra("SubTitle"));
        description.setText(getIntent().getStringExtra("Description"));

    }
}