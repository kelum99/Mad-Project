package com.example.progym.user.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.progym.Home;
import com.example.progym.MainActivity;
import com.example.progym.R;

public class MyProfileFragment extends Fragment {

    EditText name, age, address, phone, email, nic, memberType, username, password;
    TextView tv_userName;
    Button btn_updateProfile, btn_cal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.myprofile_fragment, container, false);

        name = view.findViewById(R.id.name);
        age = view.findViewById(R.id.age);
        address = view.findViewById(R.id.address);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);
        nic = view.findViewById(R.id.nic);
        memberType = view.findViewById(R.id.memberType);
        username = view.findViewById(R.id.userName);
        password = view.findViewById(R.id.password);
        tv_userName = view.findViewById(R.id.tv_userName);
        btn_updateProfile = view.findViewById(R.id.btn_updateProfile);
        btn_cal = view.findViewById(R.id.btn_cal);

        Intent intent = getActivity().getIntent();

        tv_userName.setText(intent.getStringExtra("username"));
        name.setText(intent.getStringExtra("name"));
        age.setText(intent.getStringExtra("age"));
        address.setText(intent.getStringExtra("address"));
        phone.setText(intent.getStringExtra("phone"));
        email.setText(intent.getStringExtra("email"));
        nic.setText(intent.getStringExtra("nic"));
        memberType.setText(intent.getStringExtra("memberType"));
        username.setText(intent.getStringExtra("username"));
        password.setText(intent.getStringExtra("password"));

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FitnessCalculation.class);
                startActivity(i);
            }
        });


        return  view;
    }
}