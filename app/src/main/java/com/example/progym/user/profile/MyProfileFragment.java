package com.example.progym.user.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.progym.Home;
import com.example.progym.MainActivity;
import com.example.progym.R;
import com.example.progym.admin.exercises.UpdateDeleteExercise;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileFragment extends Fragment {

    EditText name, age, address, phone, email, nic, memberType, username, password;
    TextView tv_userName;
    Button btn_updateProfile, btn_cal;
    DatabaseReference proGymMembers;

    CircleImageView profileImage;
    ImageButton changePic;
    StorageReference storageRef;
    String Username;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.myprofile_fragment, container, false);

         Username = getActivity().getIntent().getStringExtra("username").toString();
        proGymMembers = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Members").child(Username);
        storageRef = FirebaseStorage.getInstance().getReference();


        StorageReference profileRef = storageRef.child("members/"+Username+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });

        profileImage = view.findViewById(R.id.profileImage);
        changePic = view.findViewById(R.id.btn_changePic);

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

        btn_updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proGymMembers.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        snapshot.getRef().child("name").setValue(name.getText().toString());
                        snapshot.getRef().child("age").setValue(age.getText().toString());
                        snapshot.getRef().child("address").setValue(address.getText().toString());
                        snapshot.getRef().child("phone").setValue(phone.getText().toString());
                        snapshot.getRef().child("email").setValue(email.getText().toString());
                        snapshot.getRef().child("nic").setValue(nic.getText().toString());
                        snapshot.getRef().child("memberType").setValue(memberType.getText().toString());
                        snapshot.getRef().child("username").setValue(username.getText().toString());
                        snapshot.getRef().child("password").setValue(password.getText().toString());

                        Toast.makeText(getActivity(), "Update Exercise Successfully!", Toast.LENGTH_SHORT)
                                .show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(getImage,1000);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profileImage.setImageURI(imageUri);
                uploadProPic(imageUri);
            }
        }
    }

    private void uploadProPic(Uri imageUri) {
        StorageReference fileRef = storageRef.child("members/"+Username+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);
                        Toast.makeText(getActivity(), "Profile Image Upload Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Profile Image Upload Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}