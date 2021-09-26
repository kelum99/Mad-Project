package com.example.progym.user.payment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progym.R;
import com.example.progym.admin.exercises.Exercise;
import com.example.progym.admin.member.Member;
import com.example.progym.admin.schedules.Schedule;
import com.example.progym.user.schedule.ScheduleUserView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
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
    ListView cardList, transactionList;
    ArrayList<String> cardListItems;
    ArrayList<String> transactionListItems;
    boolean verify;
    Transactions transactions;
    DatabaseReference proGymFinance;
    DatabaseReference proGymFinance2;
    String formatDate;
    FirebaseListAdapter adapter;
    FirebaseListAdapter adapter2;

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
        cardList = findViewById(R.id.savedCards);
        transactionList = findViewById(R.id.transactions);
        transactionListItems = new ArrayList<String>();
        cardListItems = new ArrayList<String>();

        proGymFinance = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Transactions");
        proGymFinance2 = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Cards");

        FirebaseListOptions<Payment> options = new FirebaseListOptions.Builder<Payment>()
                .setLayout(R.layout.card_list_view)
                .setQuery(proGymFinance2,Payment.class)
                .build();
        adapter = new FirebaseListAdapter(options) {

            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView card = v.findViewById(R.id.tv_card);
                Payment payment = (Payment) model;
                card.setText(payment.getCardNumber());
            }
        };
        cardList.setAdapter(adapter);

        cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditPayment.class);
                Payment pay = (Payment) parent.getItemAtPosition(position);

                intent.putExtra("PayMethod",pay.getPaymentMethod());
                intent.putExtra("CardNum", pay.getCardNumber());
                intent.putExtra("CardHolderName", pay.getCardHolderName());
                intent.putExtra("ExpDate", pay.getExpDate());
                intent.putExtra("CVV", pay.getCvv());

                startActivity(intent);

            }
        });

        FirebaseListOptions<Transactions> op = new FirebaseListOptions.Builder<Transactions>()
                .setLayout(R.layout.transaction_list_view)
                .setQuery(proGymFinance,Transactions.class)
                .build();
        adapter2 = new FirebaseListAdapter(op) {

            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView tv_amount = v.findViewById(R.id.tv_amount);
                TextView tv_date = v.findViewById(R.id.tv_date);
                Transactions transactions = (Transactions) model;
                tv_amount.setText(transactions.getAmount());
                tv_date.setText(transactions.getDateTime());
            }
        };
        transactionList.setAdapter(adapter2);

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
                cardList.setVisibility(View.VISIBLE);
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionList.setVisibility(View.VISIBLE);
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
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        adapter2.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        adapter2.stopListening();
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