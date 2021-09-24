package com.example.progym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.progym.admin.AddEvent;

import com.example.progym.admin.Diet.AddDiet;
import com.example.progym.admin.exercises.ExerciseManagment;
import com.example.progym.admin.schedules.AddSchedule;


public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

    }

    public void buttonPress(View  v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.exerciseMngBtn:
                intent = new Intent(getApplicationContext(), ExerciseManagment.class);
                startActivity(intent);
                break;
            case R.id.memberMngBtn:
                intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                break;
            case R.id.storeMngBtn:
//                intent = new Intent(getApplicationContext(),AdminHome.class);
//                startActivity(intent);
                break;
            case R.id.scheduleMngBtn:
                intent = new Intent(getApplicationContext(), AddSchedule.class);
                startActivity(intent);
                break;
          case R.id.dietMngBtn:
               intent = new Intent(getApplicationContext(), AddDiet.class);
               startActivity(intent);
                break;
            case R.id.eventMngBtn:
                 intent = new Intent(getApplicationContext(), AddEvent.class);
                 startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    };

}