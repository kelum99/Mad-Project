package com.example.progym.admin.Diet;


import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.progym.R;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddDiet extends AppCompatActivity {
    private EditText dietTitle;
    private EditText dietID;
    private EditText dietDescription;
    private EditText breakfast;
    private EditText lunch;
    private EditText dinner;
    private EditText snack;
    Button addDiet;
    Diet diet;
    DatabaseReference dietRef;



    public AddDiet() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diet);

        dietTitle = findViewById(R.id.dietTitle);
        dietID = findViewById(R.id.dietID);
        dietDescription = findViewById(R.id.dietDes);
        breakfast = findViewById(R.id.breakfask);
        lunch = findViewById(R.id.lunch);
        dinner = findViewById(R.id.dinner);
        snack = findViewById(R.id.snack);
        addDiet = findViewById(R.id.addDiet);


        dietRef = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Diet");

        addDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDiet();
                clearFields();
            }
        });

    }

    private void insertDiet() {
        diet = new Diet();

        diet.setTitle(dietTitle.getText().toString().trim());
        diet.setDietID(dietID.getText().toString().trim());
        diet.setDescription(dietDescription.getText().toString());
        diet.setBreakfast(breakfast.getText().toString().trim());
        diet.setLunch(lunch.getText().toString());
        diet.setDinner(dinner.getText().toString());
        diet.setSnack(snack.getText().toString());


        dietRef.child(dietID.getText().toString().trim()).setValue(diet);
        Toast.makeText(this, "Diet Added!", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        dietTitle.setText("");
        dietID.setText("");
        dietDescription.setText("");
        breakfast.setText("");
        lunch.setText("");
        dinner.setText("");
        snack.setText("");
    }

    private boolean validations(){
        if(dietID.length() == 0){
            Toast.makeText(getApplicationContext(), "Please Enter Exercise ID", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dietTitle.length() == 0){
            Toast.makeText(getApplicationContext(), "Please Enter Exercise Title", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dietDescription.length() == 0){
            Toast.makeText(getApplicationContext(), "Please Enter Exercise Sub Title", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(breakfast.length() == 0){
            Toast.makeText(getApplicationContext(), "Please Enter Exercise Title", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(lunch.length() == 0){
            Toast.makeText(getApplicationContext(), "Please Enter Exercise Sub Title", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dinner.length() == 0){
            Toast.makeText(getApplicationContext(), "Please Enter Exercise Sub Title", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(snack.length() == 0){
            Toast.makeText(getApplicationContext(), "Please Enter Exercise Description", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



    }

