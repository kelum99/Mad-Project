package com.example.progym.user.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progym.R;
import com.example.progym.admin.exercises.Exercise;
import com.example.progym.admin.store.Store;
import com.example.progym.user.exercises.ExerciseFragment;
import com.example.progym.user.exercises.ExerciseUserView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class StoreFragment extends Fragment {

    View view;
    RecyclerView itemList;
    DatabaseReference proGym;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.store_fragment, container, false);
        itemList = view.findViewById(R.id.ItemRVlist);
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Store");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Store> options =
                new FirebaseRecyclerOptions.Builder<Store>()
                        .setQuery(proGym, Store.class)
                        .build();

        FirebaseRecyclerAdapter<Store, StoreFragment.RequestViewHolder> adapter = new FirebaseRecyclerAdapter<Store, StoreFragment.RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull StoreFragment.RequestViewHolder holder, int position, @NonNull Store model) {

                Picasso.get().setLoggingEnabled(true);
                holder.title.setText(model.getItem_title());
                holder.price.setText(model.getItem_price());
                Picasso.get().load(model.getImageURL()).into(holder.img);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), StoreUserView.class);

                        intent.putExtra("Title", model.getItem_title());
                        intent.putExtra("Description", model.getItem_description());
                        intent.putExtra("ImgUrl", model.getImageURL());
                        intent.putExtra("Price",model.getItem_price());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public StoreFragment.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_user_view_card, parent, false);
                StoreFragment.RequestViewHolder holder = new StoreFragment.RequestViewHolder(v);
                return holder;
            }
        };
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        itemList.setLayoutManager(llm);
        itemList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView img;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitleTV);
            price = itemView.findViewById(R.id.itemPriceTV);
            img = itemView.findViewById(R.id.itemIV);
        }
    }


}