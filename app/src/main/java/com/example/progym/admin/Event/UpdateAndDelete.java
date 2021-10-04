package com.example.progym.admin.Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progym.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateAndDelete extends AppCompatActivity {

    EditText updateEventType, updateEventID, updateEventDes, editTextDate, editTextTime;
    DatabaseReference proGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        String key = getIntent().getStringExtra("Key");
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Event").child(key);
        updateEventType = findViewById(R.id.updateEventType);
        updateEventDes = findViewById(R.id.updateEventDes);
        editTextDate = findViewById(R.id.updateEventDate);
        editTextTime = findViewById(R.id.updateEventTime);

        updateEventType.setText(getIntent().getStringExtra("Type"));
        updateEventDes.setText(getIntent().getStringExtra("Description"));
        editTextDate.setText(getIntent().getStringExtra("Date"));
        editTextTime.setText(getIntent().getStringExtra("Time")); }
    public  void updateEventBtn(View view) {
        proGym.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child("eventType").setValue(updateEventType.getText().toString());
                snapshot.getRef().child("eventDescription").setValue( updateEventDes.getText().toString());
                snapshot.getRef().child("eventDate").setValue(editTextDate.getText().toString());
                snapshot.getRef().child("eventTime").setValue( editTextTime.getText().toString());
                Toast.makeText(getApplicationContext(), "Update Event Successfully!", Toast.LENGTH_SHORT).show();
                UpdateAndDelete.this.finish(); }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});
    }

    public void dltEventBtn(View view){
        proGym.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Event Deleted Successfully!", Toast.LENGTH_SHORT).show();
                    UpdateAndDelete.this.finish();
                } else{
                    Toast.makeText(getApplicationContext(), "Event Not Deleted!", Toast.LENGTH_SHORT).show();
                }
            }});
    }
}
