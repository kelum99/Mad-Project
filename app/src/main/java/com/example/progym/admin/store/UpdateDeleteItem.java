package com.example.progym.admin.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progym.R;
import com.example.progym.admin.exercises.UpdateDeleteExercise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeleteItem extends AppCompatActivity {

    boolean Validation;

    EditText title, price, description;
    Button updateBtn;
    Button deleteBtn;
    DatabaseReference proGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_item);

        String key = getIntent().getStringExtra("ItemTitle");
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference().child("Store").child(key);
        title = findViewById(R.id.item_update_delete_title_txt);
        price = findViewById(R.id.item_update_delete_price_txt);
        description = findViewById(R.id.item_update_delete_description_txt);
        updateBtn = findViewById(R.id.item_update_delete_btn_update);
        deleteBtn = findViewById(R.id.item_update_delete_btn_delete);

        title.setText(getIntent().getStringExtra("ItemTitle"));
        price.setText(getIntent().getStringExtra("ItemPrice"));
        description.setText(getIntent().getStringExtra("ItemDesc"));

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation = ItemValidations();
                if(Validation) {
                    itemUpdate();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemDelete();
            }
        });


    }

    public  void itemUpdate() {
        proGym.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                snapshot.getRef().child("item_title").setValue(title.getText().toString());
                snapshot.getRef().child("item_price").setValue(price.getText().toString());
                snapshot.getRef().child("item_description").setValue(description.getText().toString());
                Toast.makeText(getApplicationContext(), "Item Updated Successfully!", Toast.LENGTH_SHORT).show();
                UpdateDeleteItem.this.finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void itemDelete(){
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

    //validations
    private boolean ItemValidations() {
        if (title.length() == 0) {
            title.setError("Title is required");
            return false;
        }
        if (price.length() == 0) {
            price.setError("Price is required");
            return false;
        }
        if (description.length() == 0) {
            description.setError("Description is required");
            return false;
        }

        return true;
    }
}