package com.example.progym.user.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.progym.R;

public class PaymentFragment extends Fragment {

    EditText et_cno, et_cvv,et_expdate,et_cname;
    Button btn_pay,btn_continue;
    boolean verify;
    ImageView img;
    boolean click;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.payment_fragment, container, false);

        et_cno = view.findViewById(R.id.et_cno);
        et_cvv = view.findViewById(R.id.et_cvv);
        btn_pay = view.findViewById(R.id.btn_edit);
        et_cname = view.findViewById(R.id.et_cname);
        et_expdate = view.findViewById(R.id.et_expdate);

        btn_continue = view.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MyPayment.class);
                startActivity(intent);
            }
        });

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify = validations();

                if(verify){

                            Toast.makeText(getActivity(), "You Card Details Are Being Saved", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), MyPayment.class);
                            startActivity(intent);
                        }
            }
        });

        return view;
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
        if(et_expdate.length() == 0){
            et_expdate.setError("Expiry Date is required");
            return  false;
        }
        if(et_cname.length() == 0){
            et_cname.setError("Card Holder's Name is required");
            return  false;
        }
        return true;
    }
}