package com.example.progym.admin.store;

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

public class UpdateDeleteItem extends AppCompatActivity {
        EditText itemTitle, itemPrice, itemDescription;
        Button updateBtn;
        Button deleteBtn;
        DatabaseReference proGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_item);

        String key = getIntent().getStringExtra("Key").toString();
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Exercises").child(key);
        itemTitle = findViewById(R.id.updateExerciseTitleTxt);
        itemPrice = findViewById(R.id.UpdateExerciseSitleTxt);
        itemDescription = findViewById(R.id.UpdateExerciseDesTxt);

        itemTitle.setText(getIntent().getStringExtra("Title"));
        itemPrice.setText(getIntent().getStringExtra("SubTitle"));
        itemDescription.setText(getIntent().getStringExtra("Description"));

    }
    public  void btnUpdate(View view) {
        proGym.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                snapshot.getRef().child("title").setValue(itemTitle.getText().toString());
                snapshot.getRef().child("subTitle").setValue(itemPrice.getText().toString());
                snapshot.getRef().child("description").setValue(itemDescription.getText().toString());
                Toast.makeText(getApplicationContext(), "Item Updated Successfully!", Toast.LENGTH_SHORT).show();
                UpdateDeleteItem.this.finish();
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
                    Toast.makeText(getApplicationContext(), "Item Deleted Successfully!", Toast.LENGTH_SHORT).show();
                    UpdateDeleteItem.this.finish();
                } else{
                    Toast.makeText(getApplicationContext(), "Item Not Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}