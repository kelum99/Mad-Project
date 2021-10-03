package com.example.progym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progym.user.profile.MyProfileFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    boolean isValid = false;
    String adminUsername = "admin";
    String adminPassword = "admin123";
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reference = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Members");

        Button loginBtn = findViewById(R.id.loginBtn);
        eUsername = findViewById(R.id.eUsername);
        ePassword = findViewById(R.id.ePassword);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = eUsername.getText().toString();
                String Password = ePassword.getText().toString();

                if (Username.equals(adminUsername) && Password.equals(adminPassword)) {
                    Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                    startActivity(intent);
                } else {

                    if (Username.isEmpty() || Password.isEmpty()) {
                        String errorMessage1 = "Please Enter All the Credentials!";
                        Toast.makeText(MainActivity.this, errorMessage1, Toast.LENGTH_SHORT).show();
                    } else {

                        reference.orderByChild("username").equalTo(Username).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {

                                    eUsername.setError(null);
                                    String pass = snapshot.child(Username).child("password").getValue(String.class);

                                    if (pass.equals(Password)) {

                                        ePassword.setError(null);

                                        String name = snapshot.child(Username).child("name").getValue(String.class);
                                        String age = snapshot.child(Username).child("age").getValue(String.class);
                                        String address = snapshot.child(Username).child("address").getValue(String.class);
                                        String phone = snapshot.child(Username).child("phone").getValue(String.class);
                                        String email = snapshot.child(Username).child("email").getValue(String.class);
                                        String nic = snapshot.child(Username).child("nic").getValue(String.class);
                                        String memberType = snapshot.child(Username).child("memberType").getValue(String.class);
                                        String username = snapshot.child(Username).child("username").getValue(String.class);


                                        Intent intent = new Intent(MainActivity.this, Home.class);

                                        intent.putExtra("name", name);
                                        intent.putExtra("age", age);
                                        intent.putExtra("address", address);
                                        intent.putExtra("phone", phone);
                                        intent.putExtra("email", email);
                                        intent.putExtra("nic", nic);
                                        intent.putExtra("memberType", memberType);
                                        intent.putExtra("username", username);
                                        intent.putExtra("password", pass);

                                        startActivity(intent);
                                        Toast.makeText(MainActivity.this, "Login Successful ! ", Toast.LENGTH_SHORT).show();
                                    } else {
                                        ePassword.setError("Invalid Password");
                                        ePassword.requestFocus();
                                        Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    eUsername.setError("Invalid User");
                                    eUsername.requestFocus();
                                    Toast.makeText(MainActivity.this, "No such user exists ", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                }
            }
        });

    }

}