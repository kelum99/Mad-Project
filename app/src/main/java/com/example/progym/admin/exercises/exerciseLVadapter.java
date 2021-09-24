package com.example.progym.admin.exercises;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.progym.R;

import java.util.ArrayList;


public class exerciseLVadapter extends ArrayAdapter<Exercise> {
    public exerciseLVadapter(@NonNull Context context, ArrayList<Exercise> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_list_view, parent, false);
        }

        Exercise exercise = getItem(position);

        TextView titleTV = listitemView.findViewById(R.id.exNameTV);
        TextView sTitleTV = listitemView.findViewById(R.id.exSNameTV);

        titleTV.setText(exercise.getTitle());
        sTitleTV.setText(exercise.getSubTitle());

        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Item clicked is : " + exercise.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return listitemView;
    }
}

