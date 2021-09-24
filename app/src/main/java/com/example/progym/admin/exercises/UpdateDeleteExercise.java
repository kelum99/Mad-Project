package com.example.progym.admin.exercises;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.progym.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    public  void btnUpdate(View view) {
        proGym.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                snapshot.getRef().child("title").setValue(title.getText().toString());
                snapshot.getRef().child("subTitle").setValue(subTitle.getText().toString());
                snapshot.getRef().child("description").setValue(description.getText().toString());
                Toast.makeText(getApplicationContext(), "Update Exercise Successfully!", Toast.LENGTH_SHORT).show();
                UpdateDeleteExercise.this.finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void btnDelete(View view){
        proGym.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Exercise Deleted Successfully!", Toast.LENGTH_SHORT).show();
                    UpdateDeleteExercise.this.finish();
                } else{
                    Toast.makeText(getApplicationContext(), "Exercise Not Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}