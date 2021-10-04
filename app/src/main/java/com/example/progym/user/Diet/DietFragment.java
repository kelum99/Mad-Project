package com.example.progym.user.Diet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.progym.R;
import com.example.progym.admin.Diet.Diet;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DietFragment extends Fragment {
    View view;
    ListView dietList;
    DatabaseReference proGym;
    FirebaseListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diet_fragment, container, false);

        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Diet");
        dietList = view.findViewById(R.id.userDietLV);
        FirebaseListOptions<Diet> options = new FirebaseListOptions.Builder<Diet>()
                .setLayout(R.layout.dietlist)
                .setQuery(proGym, Diet.class)
                .build();
        adapter = new FirebaseListAdapter(options) {

            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView Type = v.findViewById(R.id.d_Type);
                TextView key = v.findViewById(R.id.d_ID);
                Diet diet = (Diet) model;
                Type.setText(diet.getTitle());
                key.setText(diet.getDietID());

            }
        };
        dietList.setAdapter(adapter);

        dietList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DietUserView.class);
                Diet diet = (Diet) parent.getItemAtPosition(position);

                intent.putExtra("Title", diet.getTitle());
                intent.putExtra("Des", diet.getDescription());
                intent.putExtra("Breakfast", diet.getBreakfast());
                intent.putExtra("Lunch", diet.getLunch());
                intent.putExtra("Dinner", diet.getDinner());
                intent.putExtra("Snacks", diet.getSnack());
                intent.putExtra("Key", diet.getDietID());

                startActivity(intent);

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}