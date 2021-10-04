package com.example.progym.user.Event;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progym.R;
import com.example.progym.admin.Event.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EventFragment extends Fragment {

    View view;
    DatabaseReference proGym;
    RecyclerView eventRVlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_fragment, container, false);
        eventRVlist = view.findViewById(R.id.eventRVlist);
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Event");
        return view; }
        @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Event> options =
                new FirebaseRecyclerOptions.Builder<Event>()
                        .setQuery(proGym, Event.class)
                        .build();
        FirebaseRecyclerAdapter<Event, RequestViewHolder> adapter = new FirebaseRecyclerAdapter<Event, RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RequestViewHolder holder, int position, @NonNull Event model) {
                Picasso.get().setLoggingEnabled(true);
                holder.title.setText(model.getEventType());
                Picasso.get().load(model.getImageEvent()).into(holder.img);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), EventUserView.class);
                        intent.putExtra("Key", model.getEventID());
                        intent.putExtra("Title", model.getEventType());
                        intent.putExtra("Description", model.getEventDescription());
                        intent.putExtra("ImgUrl", model.getImageEvent());
                        intent.putExtra("Date", model.getEventDate());
                        intent.putExtra("Time", model.getEventTime());
                        startActivity(intent); }}); }
            @NonNull
            @Override
            public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_user_view_card, parent, false);
                RequestViewHolder holder = new RequestViewHolder(v);
                return holder; }};
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        eventRVlist.setLayoutManager(llm);
        eventRVlist.setAdapter(adapter);
        adapter.startListening(); }
        public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.eventTitleTV);
            img = itemView.findViewById(R.id.eventIV); }}
}