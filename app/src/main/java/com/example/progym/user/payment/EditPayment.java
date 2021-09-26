package com.example.progym.user.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progym.R;
import com.example.progym.admin.exercises.UpdateDeleteExercise;
import com.example.progym.admin.member.DeleteMember;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditPayment extends AppCompatActivity {

    DatabaseReference proGymFinance;
    EditText et_method, et_cname, et_cno, et_expDate, et_cvv;
    Button btn_edit, btn_dlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment);

        String key = getIntent().getStringExtra("CardNum");
        proGymFinance = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Cards").child(key);

        et_method = findViewById(R.id.et_method);
        et_cname = findViewById(R.id.et_cname);
        et_cno = findViewById(R.id.et_cno);
        et_expDate = findViewById(R.id.et_expDate);
        et_cvv = findViewById(R.id.et_cvv);
        btn_edit = findViewById(R.id.btn_edit);
        btn_dlt = findViewById(R.id.btn_dlt);

        et_method.setText(getIntent().getStringExtra("PayMethod"));
        et_cname.setText(getIntent().getStringExtra("CardHolderName"));
        et_cno.setText(getIntent().getStringExtra("CardNum"));
        et_expDate.setText(getIntent().getStringExtra("ExpDate"));
        et_cvv.setText(getIntent().getStringExtra("CVV"));

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateCard();
            }
        });

        btn_dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditPayment.this);
                alert.setTitle("Are you sure you want to delete this card?");

                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                       DeleteCard();
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
    public  void UpdateCard() {
        proGymFinance.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                snapshot.getRef().child("cardHolderName").setValue(et_cname.getText().toString());
                snapshot.getRef().child("cardNumber").setValue(et_cno.getText().toString());
                snapshot.getRef().child("cvv").setValue(et_cvv.getText().toString());
                snapshot.getRef().child("expDate").setValue(et_expDate.getText().toString());
                snapshot.getRef().child("paymentMethod").setValue(et_method.getText().toString());
                Toast.makeText(getApplicationContext(), "Update Card Successfully!", Toast.LENGTH_SHORT).show();
                EditPayment.this.finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void DeleteCard(){
        proGymFinance.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Exercise Deleted Successfully!", Toast.LENGTH_SHORT).show();
                    EditPayment.this.finish();
                } else{
                    Toast.makeText(getApplicationContext(), "Exercise Not Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}