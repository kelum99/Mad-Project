package com.example.progym.admin.member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progym.R;
import com.example.progym.admin.exercises.UpdateDeleteExercise;
import com.example.progym.user.payment.MyPayment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteMember extends AppCompatActivity {
    DatabaseReference proGym;
    TextView mName, mAge, mAddress, mMember, mPhone, mEmail, mNic, mUsername, mPassword, mGender;
    Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_member);

        String key = getIntent().getStringExtra("Username");
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Members").child(key);

        mName = findViewById(R.id.mName);
        mGender = findViewById(R.id.mGender);
        mAge = findViewById(R.id.mAge);
        mAddress = findViewById(R.id.mAddress);
        mMember = findViewById(R.id.mMember);
        mPhone = findViewById(R.id.mPhone);
        mEmail = findViewById(R.id.mEmail);
        mNic = findViewById(R.id.mNic);
        mUsername = findViewById(R.id.mUsername);
        mPassword = findViewById(R.id.mPassword);
        btn_delete = findViewById(R.id.btn_delete);

        mName.setText(getIntent().getStringExtra("Name"));
        mGender.setText(getIntent().getStringExtra("Gender"));
        mAge.setText(getIntent().getStringExtra("Age"));
        mAddress.setText(getIntent().getStringExtra("Address"));
        mMember.setText(getIntent().getStringExtra("MemberType"));
        mPhone.setText(getIntent().getStringExtra("Phone"));
        mEmail.setText(getIntent().getStringExtra("Email"));
        mNic.setText(getIntent().getStringExtra("NIC"));
        mUsername.setText(getIntent().getStringExtra("Username"));
        mPassword.setText(getIntent().getStringExtra("Password"));

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DeleteMember.this);
                alert.setTitle("Are you sure you want to delete this member?");

                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        DeleteMember();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertdialog = alert.create();
                alertdialog.show();

            }
        });
    }

        public void DeleteMember () {
            proGym.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Member Deleted Successfully!", Toast.LENGTH_SHORT).show();
                        DeleteMember.this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Member Not Deleted!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


}