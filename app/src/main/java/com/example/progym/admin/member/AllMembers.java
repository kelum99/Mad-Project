package com.example.progym.admin.member;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progym.R;
import com.example.progym.admin.exercises.AddExercise;
import com.example.progym.admin.exercises.Exercise;
import com.example.progym.admin.exercises.UpdateDeleteExercise;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class AllMembers extends AppCompatActivity {

    Button addNewMember;
    ListView memberList;
    DatabaseReference proGym;
    ArrayList<String> memberListItems;
    FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_members);

        Query proGym = FirebaseDatabase.getInstance
                ("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").
                getReference().child("Members");
        memberList = findViewById(R.id.allMembers);
        memberListItems = new ArrayList<>();
        addNewMember = findViewById(R.id.btn_addnewmem);

        FirebaseListOptions<Member> options = new FirebaseListOptions.Builder<Member>()
                .setLayout(R.layout.member_list_view)
                .setQuery(proGym, Member.class)
                .build();
        adapter = new FirebaseListAdapter(options) {

            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView uname = v.findViewById(R.id.tv_memberUname);
                TextView pwd = v.findViewById(R.id.tv_memberPwd);
                Member member = (Member) model;
                uname.setText(member.getUsername());
                pwd.setText(member.getPassword());
            }
        };
        memberList.setAdapter(adapter);

        addNewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMember.class);
                startActivity(intent);
            }
        });

        memberList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent Delete = new Intent(getApplicationContext(), DeleteMember.class);
                Member mem = (Member) parent.getItemAtPosition(position);

                Delete.putExtra("Name", mem.getName());
                Delete.putExtra("Gender", mem.getGender());
                Delete.putExtra("Age", mem.getAge());
                Delete.putExtra("Address", mem.getAddress());
                Delete.putExtra("Phone", mem.getPhone());
                Delete.putExtra("Email", mem.getEmail());
                Delete.putExtra("NIC", mem.getNic());
                Delete.putExtra("MemberType", mem.getMemberType());
                Delete.putExtra("Username", mem.getUsername());
                Delete.putExtra("Password", mem.getPassword());

                startActivity(Delete);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}