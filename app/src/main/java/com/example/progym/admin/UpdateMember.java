package com.example.progym.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.progym.R;

public class UpdateMember extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);

        Spinner dropdown = findViewById(R.id.membership);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Membership,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }


    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}