package com.example.progym.user.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.progym.R;

public class StoreFragment extends Fragment {

    ImageButton cartBtn;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.store_fragment, container, false);

        cartBtn = view.findViewById(R.id.cart_img);
        btn = view.findViewById(R.id.buy_now);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "0000000", Toast.LENGTH_SHORT).show();
            }
        });



        return view;


    }


}