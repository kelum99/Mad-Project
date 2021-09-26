package com.example.progym.admin.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.progym.R;
import com.example.progym.admin.exercises.Exercise;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMember extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText et_name, et_age, et_address, et_nic, et_phone, et_email, et_username, et_password;
    RadioGroup gender;
    RadioButton selectedGender;
    Spinner member;
    String selectedMember;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button btn_addmember;
    boolean verify;
    DatabaseReference proGym;
    Member newMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        member = findViewById(R.id.membership);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Membership, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        member.setAdapter(adapter);
        member.setOnItemSelectedListener(this);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_address = findViewById(R.id.et_address);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_nic = findViewById(R.id.et_nic);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_addmember = findViewById(R.id.btn_addmember);

        gender = findViewById(R.id.rg_gender);

        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Members");

        btn_addmember.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                verify = validations();

                if (verify) {
                    addMember(selectedMember);
                    clearFields();
                    Intent intent = new Intent(getApplicationContext(), AllMembers.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void addMember(String selectedMember) {
        newMember = new Member();

        newMember.setName(et_name.getText().toString().trim());
        newMember.setGender(selectedGender.getText().toString().trim());
        newMember.setAge(et_age.getText().toString().trim());
        newMember.setAddress(et_address.getText().toString().trim());
        newMember.setPhone(et_phone.getText().toString().trim());
        newMember.setEmail(et_email.getText().toString().trim());
        newMember.setNic(et_nic.getText().toString().trim());
        newMember.setMemberType(selectedMember);
        newMember.setUsername(et_username.getText().toString().trim());
        newMember.setPassword(et_password.getText().toString().trim());

        proGym.child(et_username.getText().toString().trim()).setValue(newMember);
        Toast.makeText(this, "Member is Added!", Toast.LENGTH_SHORT).show();
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedMember = member.getSelectedItem().toString();
    }


    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void radioButtonClick(View view) {
        int radioID = gender.getCheckedRadioButtonId();
        selectedGender = findViewById(radioID);
    }

    private void clearFields() {
        et_name.setText("");
        et_age.setText("");
        et_address.setText("");
        et_phone.setText("");
        et_email.setText("");
        et_nic.setText("");
        et_username.setText("");
        et_password.setText("");

    }

    private boolean validations() {
        if (et_name.length() == 0) {
            et_name.setError("Please Enter Name");
            return false;
        }
        if (et_age.length() == 0) {
            et_age.setError("Please Enter Age");
            return false;
        }
        if (et_address.length() == 0) {
            et_address.setError("Please Enter Address");
            return false;
        }
        if (et_phone.length() == 0) {
            et_phone.setError("Please Enter Mobile Number");
            return false;
        }
        if (et_phone.length() != 10) {
            et_phone.setError("Invalid Mobile Number");
            return false;
        }
        if (et_email.length() == 0) {
            et_email.setError("Please Enter Email");
            return false;
        }
        if (!et_email.getText().toString().trim().matches(emailPattern)) {
            et_email.setError("Invalid Email");
            return false;
        }
        if (et_nic.length() == 0) {
            et_nic.setError("Please Enter NIC");
            return false;
        }
        if (et_username.length() == 0) {
            et_username.setError("Please Enter Username");
            return false;
        }
        if ((et_password.length() <= 8) || (et_password.length() >= 16)) {
            et_password.setError("Please Enter Valid Password");
            return false;
        }
        return true;
    }
}