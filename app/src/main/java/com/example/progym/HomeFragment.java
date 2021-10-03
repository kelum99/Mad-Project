package com.example.progym;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.progym.user.Event.EventFragment;
import com.example.progym.user.exercises.ExerciseFragment;
import com.example.progym.user.schedule.ScheduleFragment;

public class HomeFragment extends Fragment {

    View view;
    Button seeAll;
    ImageButton atHome, atGym;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.home_fragment, container, false);

        atGym = view.findViewById(R.id.gymExercise);
        atHome = view.findViewById(R.id.homeExercise);
        seeAll = view.findViewById(R.id.seeAll);

        atGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ScheduleFragment scheduleFragment = new ScheduleFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, scheduleFragment);
                fragmentTransaction.commit();
            }
        });

        atHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ExerciseFragment exerciseFragment = new ExerciseFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, exerciseFragment);
                fragmentTransaction.commit();
            }
        });

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                EventFragment eventFragment = new EventFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, eventFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}