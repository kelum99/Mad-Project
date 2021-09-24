package com.example.progym.user.payment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.progym.R;
import com.example.progym.admin.exercises.Exercise;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyPayment extends AppCompatActivity {

    Button btn_viewcards, btn_paynow, btn_history,btn_confirm;
    EditText et_amount;
    ListView rv_cards, rv_transactions;
    ArrayList<String> cardListItems;
    ArrayList<String> transactionListItems;
    boolean verify;
    Transactions transactions;
    DatabaseReference proGymFinance;
    DatabaseReference proGymFinance2;
    String formatDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_payment);

        Date current = Calendar.getInstance().getTime();
         formatDate = DateFormat.getDateInstance(DateFormat.FULL).format(current);


        btn_viewcards = findViewById(R.id.btn_viewcards);
        btn_paynow = findViewById(R.id.btn_paynow);
        btn_history = findViewById(R.id.btn_history);
        btn_confirm = findViewById(R.id.btn_confirm);
        et_amount = findViewById(R.id.et_amount);
        rv_cards = findViewById(R.id.savedCards);
        rv_transactions = findViewById(R.id.transactions);
        transactionListItems = new ArrayList<String>();
        cardListItems = new ArrayList<String>();

        proGymFinance = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Transactions");
        proGymFinance2 = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Cards");

        btn_paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_amount.setVisibility(View.VISIBLE);
                btn_confirm.setVisibility(View.VISIBLE);
            }
        });

        btn_viewcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_cards.setVisibility(View.VISIBLE);
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_transactions.setVisibility(View.VISIBLE);
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify = validations();

                if(verify){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MyPayment.this);
                    alert.setTitle("Confirm Payment");
                    alert.setMessage("Are you sure you want to proceed?");
                    alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(MyPayment.this, "Your Payment is being Processed", Toast.LENGTH_LONG).show();

                            saveTransactions();
                            clearFields();

                            AlertDialog.Builder alert = new AlertDialog.Builder(MyPayment.this);
                            alert.setMessage("Your Payment is Successful ! ");
                            alert.setNeutralButton("OK",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            alert.show();

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

            }
        });

        initializeListView();
        initializeCardListView();


    }

    private void initializeListView() {

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, transactionListItems);

        proGymFinance.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String value =  snapshot.getValue(Transactions.class).toString();
                transactionListItems.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                transactionListItems.remove(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rv_transactions.setAdapter(adapter);
    }

    private void initializeCardListView() {

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cardListItems);

        proGymFinance2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String value =  snapshot.getValue(Payment.class).toString();
                cardListItems.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                cardListItems.remove(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rv_cards.setAdapter(adapter);
    }

    private void saveTransactions() {
        transactions = new Transactions();

        transactions.setAmount(et_amount.getText().toString().trim());
        transactions.setDateTime(formatDate);
        proGymFinance.push().setValue(transactions);

    }
    private void clearFields() {

        et_amount.setText("");
    }

    private boolean validations(){

        if(et_amount.length() == 0){
            et_amount.setError("You Need to Enter the Amount First");
            return false;
        }
        return true;
    }

}