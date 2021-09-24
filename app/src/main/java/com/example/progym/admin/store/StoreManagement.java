package com.example.progym.admin.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progym.R;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class StoreManagement extends AppCompatActivity {

    Button addItemBtn;
    ListView itemList;
    DatabaseReference proGym;
    ArrayList<String> ItemListItem;
    FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_management);

        Query proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Store");
        itemList = findViewById(R.id.store_management_itemLV);
        ItemListItem = new ArrayList<>();
        addItemBtn = findViewById(R.id.store_management_add_item_btn);

        FirebaseListOptions<Store> options = new FirebaseListOptions.Builder<Store>()
                .setLayout(R.layout.item_list_view)
                .setQuery(proGym, Store.class)
                .build();
        adapter = new FirebaseListAdapter(options) {

            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView itemName = v.findViewById(R.id.itemName_LV);
                TextView itemPrice = v.findViewById(R.id.itemPrice_LV);
                Store store = (Store) model;
                itemName.setText(store.getItem_title());
                itemPrice.setText(store.getItem_price());

            }
        };
        itemList.setAdapter(adapter);


        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddItem.class);
                startActivity(intent);
            }
        });

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent UpdateDelete = new Intent(getApplicationContext(), UpdateDeleteItem.class);
                Store st = (Store) parent.getItemAtPosition(position);

                UpdateDelete.putExtra("ItemTitle",st.getItem_title());
                UpdateDelete.putExtra("ItemPrice",st.getItem_price());
                UpdateDelete.putExtra("ItemDesc",st.getItem_description());


                startActivity(UpdateDelete);
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