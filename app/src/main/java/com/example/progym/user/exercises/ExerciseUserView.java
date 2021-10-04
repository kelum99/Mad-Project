package com.example.progym.user.exercises;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progym.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ExerciseUserView extends AppCompatActivity {

    TextView title, description, subTitle;
    ImageView img;

    DatabaseReference proGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_user_view);

        String key = getIntent().getStringExtra("Key");
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference().child("Exercises").child(key);

        title = findViewById(R.id.userViewExTitle);
        subTitle = findViewById(R.id.userViewExSTitle);
        description = findViewById(R.id.UserViewExDes);
        img = findViewById(R.id.userViewExImg);

        title.setText(getIntent().getStringExtra("Title"));
        subTitle.setText(getIntent().getStringExtra("SubTitle"));
        description.setText(getIntent().getStringExtra("Description"));

        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(getIntent().getStringExtra("ImgUrl")).into(img);
    }
}