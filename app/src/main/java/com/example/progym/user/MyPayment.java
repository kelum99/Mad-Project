package com.example.progym.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progym.Home;
import com.example.progym.R;

public class MyPayment extends AppCompatActivity {

    Button btn_viewcards, btn_paynow, btn_history,btn_confirm;
    EditText et_amount;
    RecyclerView rv_cards, rv_transactions;
    boolean verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_payment);

        btn_viewcards = findViewById(R.id.btn_viewcards);
        btn_paynow = findViewById(R.id.btn_paynow);
        btn_history = findViewById(R.id.btn_history);
        btn_confirm = findViewById(R.id.btn_confirm);
        et_amount = findViewById(R.id.et_amount);
        rv_cards = findViewById(R.id.rv_cards);
        rv_transactions = findViewById(R.id.rv_transactions);

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

    private boolean validations(){

        if(et_amount.length() == 0){
            et_amount.setError("You Need to Enter the Amount First");
            return false;
        }
        return true;
    }

}