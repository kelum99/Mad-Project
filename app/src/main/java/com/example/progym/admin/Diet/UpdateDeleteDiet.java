package com.example.progym.admin.Diet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progym.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeleteDiet extends AppCompatActivity {
    EditText UpTitle, UpDietID, UpDietDes, UpBreakfast, UpLunch, UpDinner, UpSnacks;

    //Button updateDietBtn;
    //Button dltDietBtn;
   DatabaseReference proGym;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_deletediet);

        String key = getIntent().getStringExtra("Key");
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Diet").child(key);
       UpTitle = findViewById(R.id.UpdietTitle);
       UpDietID = findViewById(R.id.UpdietID);
       UpDietDes = findViewById(R.id.UpdietDes);
       UpBreakfast = findViewById(R.id.Upbreakfask);
       UpLunch = findViewById(R.id.Uplunch);
       UpDinner = findViewById(R.id.Updinner);
       UpSnacks = findViewById(R.id.Upsnack);

       UpTitle.setText(getIntent().getStringExtra("Title"));
       UpDietID.setText(getIntent().getStringExtra("Key"));
       UpDietDes.setText(getIntent().getStringExtra("DietDescription"));
       UpBreakfast.setText(getIntent().getStringExtra("Breakfast"));
       UpLunch.setText(getIntent().getStringExtra("Lunch"));
       UpDinner.setText(getIntent().getStringExtra("Dinner"));
       UpSnacks.setText(getIntent().getStringExtra("Snacks"));


    }
    public  void updateDietBtn(View view) {
        proGym.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                snapshot.getRef().child("Title").setValue(UpTitle.getText().toString());
                snapshot.getRef().child("key").setValue(UpDietID.getText().toString());
                snapshot.getRef().child("DietDescription").setValue(UpDietDes.getText().toString());
                snapshot.getRef().child("Breakfast").setValue(UpBreakfast.getText().toString());
                snapshot.getRef().child("Lunch").setValue(UpLunch.getText().toString());
                snapshot.getRef().child("Dinner").setValue(UpDinner.getText().toString());
                snapshot.getRef().child("Snacks").setValue(UpSnacks.getText().toString());
                Toast.makeText(getApplicationContext(), "Update Diet Successfully!", Toast.LENGTH_SHORT).show();
                UpdateDeleteDiet.this.finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void dltDietBtn(View view){
        proGym.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Diet Deleted Successfully!", Toast.LENGTH_SHORT).show();
                    UpdateDeleteDiet.this.finish();
               } else{
                   Toast.makeText(getApplicationContext(), "Diet Not Deleted!", Toast.LENGTH_SHORT).show();
               }
            }
        });
   }
}
