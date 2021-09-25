package com.example.progym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;





import com.example.progym.admin.Event.CreateEvent;



import com.example.progym.admin.Diet.AddDiet;


import com.example.progym.admin.exercises.AddExercise;

import com.example.progym.admin.member.AddMember;
import com.example.progym.admin.member.AllMembers;


import com.example.progym.admin.exercises.ExerciseManagment;

import com.example.progym.admin.schedules.AddSchedule;
import com.example.progym.admin.store.StoreManagement;


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
                intent = new Intent(getApplicationContext(), AllMembers.class);
                startActivity(intent);
                break;
            case R.id.storeMngBtn:
                intent = new Intent(getApplicationContext(), StoreManagement.class);
                startActivity(intent);
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
                 intent = new Intent(getApplicationContext(), CreateEvent.class);
                 startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    };

}