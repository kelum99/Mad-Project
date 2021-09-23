package com.example.progym.admin.exercises;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.progym.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class ExerciseManagment extends AppCompatActivity {

    Button addExerciseBtn;
    ListView exerciseList;
    DatabaseReference proGym;
    ArrayList<String> exerciseListItems;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_managment);

        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Exercises");
        exerciseList = findViewById(R.id.exerciseLV);
        exerciseListItems = new ArrayList<String>();
        addExerciseBtn = findViewById(R.id.add_exercise);

        addExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddExercise.class);
                startActivity(intent);
            }
        });
        initializeListView();

        exerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent updateDelete = new Intent(getApplicationContext(),UpdateDeleteExercise.class);
                Exercise ex = (Exercise) parent.getItemAtPosition(position);
                updateDelete.putExtra("Title",ex.getTitle());
                updateDelete.putExtra("SubTitle",ex.getSubTitle());
                updateDelete.putExtra("Description",ex.getDescription());
                updateDelete.putExtra("Key",key);

                startActivity(updateDelete);

            }
        });

    }

    private void initializeListView() {

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, exerciseListItems);

        proGym.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String value =  Objects.requireNonNull(snapshot.getValue(Exercise.class)).toString();
                exerciseListItems.add(value);
                adapter.notifyDataSetChanged();
                key = snapshot.getKey();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                exerciseListItems.remove(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        exerciseList.setAdapter(adapter);
    }

}