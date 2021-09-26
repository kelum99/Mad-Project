package com.example.progym.user.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.progym.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PaymentFragment extends Fragment {

    EditText et_cno, et_cvv,et_expDate,et_cname,et_payMethod;
    Button btn_save,btn_continue;
    boolean verify;
    Payment payment;
    DatabaseReference ProGym;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.payment_fragment, container, false);

        et_payMethod = view.findViewById(R.id.et_payMethod);
        et_cname = view.findViewById(R.id.et_cname);
        et_cno = view.findViewById(R.id.et_cno);
        et_expDate = view.findViewById(R.id.et_expDate);
        et_cvv = view.findViewById(R.id.et_cvv);
        btn_save = view.findViewById(R.id.btn_edit);
        btn_continue = view.findViewById(R.id.btn_continue);


        ProGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Cards");


        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MyPayment.class);
                startActivity(intent);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify = validations();

                if(verify){
                            saveCard();
                            clearFields();

                            Intent intent = new Intent(getActivity(), MyPayment.class);
                            startActivity(intent);
                        }
            }
        });

        return view;
    }


    private void saveCard() {
        payment = new Payment();

        payment.setPaymentMethod(et_payMethod.getText().toString().trim());
        payment.setCardHolderName(et_cname.getText().toString().trim());
        payment.setCardNumber(et_cno.getText().toString().trim());
        payment.setExpDate(et_expDate.getText().toString().trim());
        payment.setCvv(et_cvv.getText().toString().trim());

        //ProGym.push().setValue(payment);
        ProGym.child(et_cno.getText().toString().trim()).setValue(payment);
        Toast.makeText(getActivity(), "You Card Details Are Saved", Toast.LENGTH_LONG).show();
    }
    private void clearFields() {

        et_payMethod.setText("");
        et_cname.setText("");
        et_cno.setText("");
        et_expDate.setText("");
        et_cvv.setText("");
    }

    private boolean validations(){
        if(et_cno.length() != 16){
            et_cno.setError("Please Enter 16 digit Card Number");
            return  false;
        }
        if(et_cvv.length() != 3){
            et_cvv.setError("Please Enter 3 digit Card Number");
            return  false;
        }
        if(et_expDate.length() == 0){
            et_expDate.setError("Expiry Date is required");
            return  false;
        }
        if(et_cname.length() == 0){
            et_cname.setError("Card Holder's Name is required");
            return  false;
        }
        return true;
    }

}