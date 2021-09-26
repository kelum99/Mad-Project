package com.example.progym.user.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progym.R;
import com.example.progym.admin.exercises.Exercise;
import com.example.progym.user.Event.EventUserView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ExerciseFragment extends Fragment {

    View view;
    DatabaseReference proGym;
    RecyclerView exerciseList;
   // SearchView search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.exercise_fragment, container, false);

        exerciseList = view.findViewById(R.id.exerciseRVlist);
       // search = view.findViewById(R.id.exerciseSearch);
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Exercises");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Exercise> options =
                new FirebaseRecyclerOptions.Builder<Exercise>()
                        .setQuery(proGym, Exercise.class)
                        .build();

        FirebaseRecyclerAdapter<Exercise, RequestViewHolder> adapter = new FirebaseRecyclerAdapter<Exercise, RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RequestViewHolder holder, int position, @NonNull Exercise model) {

                Picasso.get().setLoggingEnabled(true);
                holder.title.setText(model.getTitle());
                holder.subTitle.setText(model.getSubTitle());
                Picasso.get().load(model.getImageURL()).into(holder.img);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), EventUserView.class);
                        intent.putExtra("Key", model.getKey());
                        intent.putExtra("Title", model.getTitle());
                        intent.putExtra("Description", model.getDescription());
                        intent.putExtra("ImgUrl", model.getImageURL());
                        intent.putExtra("SubTitle", model.getSubTitle());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_user_view_card, parent, false);
                RequestViewHolder holder = new RequestViewHolder(v);
                return holder;
            }
        };
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        exerciseList.setLayoutManager(llm);
        exerciseList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView title, subTitle;
        ImageView img;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.exerciseTitleTV);
            subTitle = itemView.findViewById(R.id.exerciseSTitleTV);
            img = itemView.findViewById(R.id.exerciseIV);
        }
    }

}