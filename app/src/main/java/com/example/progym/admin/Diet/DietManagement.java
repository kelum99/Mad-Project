//package com.example.progym.admin.Diet;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import com.example.progym.R;
//import com.example.progym.admin.exercises.AddExercise;
//import com.example.progym.admin.exercises.Exercise;
//import com.example.progym.admin.exercises.UpdateDeleteExercise;
//import com.firebase.ui.database.FirebaseListAdapter;
//import com.firebase.ui.database.FirebaseListOptions;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//
//import java.util.ArrayList;
//
//public class DietManagement {
//    //upDietTitle, UPDietID, UpDietDes, UpBreakfask, UpLunch, UpDinner, UpSnack;
//    Button addExerciseBtn;
//    ListView exerciseList;
//    DatabaseReference proGym;
//    ArrayList<String> exerciseListItems;
//    FirebaseListAdapter adapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_exercise_managment);
//
//        Query proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Exercises");
//        exerciseList = findViewById(R.id.exerciseLV);
//        exerciseListItems = new ArrayList<>();
//        addExerciseBtn = findViewById(R.id.add_exercise);
//
//        FirebaseListOptions<Exercise> options = new FirebaseListOptions.Builder<Exercise>()
//                .setLayout(R.layout.exercise_list_view)
//                .setQuery(proGym, Exercise.class)
//                .build();
//        adapter = new FirebaseListAdapter(options) {
//
//            @Override
//            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
//                TextView title = v.findViewById(R.id.exNameTV);
//                TextView key = v.findViewById(R.id.exSNameTV);
//                Exercise exercise = (Exercise) model;
//                title.setText(exercise.getTitle());
//                key.setText(exercise.getKey());
//
//            }
//        };
//        exerciseList.setAdapter(adapter);
//
//
//        addExerciseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AddExercise.class);
//                startActivity(intent);
//            }
//        });
//
//        exerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent updateDelete = new Intent(getApplicationContext(), UpdateDeleteExercise.class);
//                Exercise ex = (Exercise) parent.getItemAtPosition(position);
//                updateDelete.putExtra("Title", ex.getTitle());
//                updateDelete.putExtra("Key", ex.getKey());
//                updateDelete.putExtra("Description", ex.getDescription());
//
//                startActivity(updateDelete);
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
//}