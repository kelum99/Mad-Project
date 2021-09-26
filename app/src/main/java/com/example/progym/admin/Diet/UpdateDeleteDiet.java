//package com.example.progym.admin.Diet;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.example.progym.R;
//import com.example.progym.admin.exercises.UpdateDeleteExercise;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class UpdateDeleteDiet {
//    EditText Title, DietID, DietDes, Breakfast, Lunch, Dinner, Snacks;
//
//    Button UpDiet;
//    Button DlDiet;
//    DatabaseReference proGym;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update_delete_diet);
//
//        String key = getIntent().getStringExtra("Key");
//        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Exercises").child(key);
//        Title = findViewById(R.id.upDietTitle);
//        DietID = findViewById(R.id.UPDietID);
//        DietDes = findViewById(R.id.UpDietDes);
//        Breakfast = findViewById(R.id.UpBreakfask);
//        Lunch = findViewById(R.id. UpLunch);
//        Dinner = findViewById(R.id.UpDinner);
//        Snacks = findViewById(R.id.UpSnack);
//
//        Title.setText(getIntent().getStringExtra("Title"));
//        DietID.setText(getIntent().getStringExtra("Key"));
//        DietDes.setText(getIntent().getStringExtra("DietDescription"));
//        Breakfast.setText(getIntent().getStringExtra("Breakfast"));
//        Lunch.setText(getIntent().getStringExtra("Lunch"));
//        Dinner.setText(getIntent().getStringExtra("Dinner"));
//        Snacks.setText(getIntent().getStringExtra("Snacks"));
//
//    }
//    public  void UpDiet(View view) {
//        proGym.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                snapshot.getRef().child("Title").setValue(Title.getText().toString());
//                snapshot.getRef().child("key").setValue(DietID.getText().toString());
//                snapshot.getRef().child("DietDescription").setValue(DietDes.getText().toString());
//                snapshot.getRef().child("Breakfast").setValue( Breakfast.getText().toString());
//                snapshot.getRef().child("Lunch").setValue(Lunch.getText().toString());
//                snapshot.getRef().child("Dinner").setValue(Dinner.getText().toString());
//                snapshot.getRef().child("Snacks").setValue(Snacks.getText().toString());
//                Toast.makeText(getApplicationContext(), "Update Diet Successfully!", Toast.LENGTH_SHORT).show();
//                UpdateDeleteDiet.this.finish();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    public void DlDiet(View view){
//        proGym.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), "Exercise Deleted Successfully!", Toast.LENGTH_SHORT).show();
//                    UpdateDeleteDiet.this.finish();
//                } else{
//                    Toast.makeText(getApplicationContext(), "Exercise Not Deleted!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}
