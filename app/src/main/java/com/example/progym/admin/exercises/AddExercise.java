package com.example.progym.admin.exercises;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progym.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddExercise extends AppCompatActivity {

    private EditText exerciseTitle;
    private EditText exerciseID;
    private EditText exerciseSubTitle;
    private EditText exerciseDescription;
    // ImageView preview;
    String imgUrl;
    Button addExercise;
    Button uploadBtn;
    Exercise exercise;
    DatabaseReference proGym;
    StorageReference exerciseImgRef;
    public Uri imgUri;
    private static final int IMAGE_REQUEST = 2;

    public AddExercise() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        exerciseTitle = findViewById(R.id.exerciseTitleTxt);
        exerciseID = findViewById(R.id.exerciseID);
        exerciseSubTitle = findViewById(R.id.exerciseSubTitleTxt);
        exerciseDescription = findViewById(R.id.exerciseDesTxt);
        addExercise = findViewById(R.id.addExercise);
        uploadBtn = findViewById(R.id.uploadBtn1);
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Exercises");

        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertExercise();
                clearFields();
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });



    }
    private void insertExercise() {
        exercise = new Exercise();

        exercise.setTitle(exerciseTitle.getText().toString().trim());
        exercise.setKey(exerciseID.getText().toString().trim());
        exercise.setDescription(exerciseDescription.getText().toString().trim());
        exercise.setSubTitle(exerciseSubTitle.getText().toString().trim());
        exercise.setImageURL(imgUrl);

        proGym.child(exerciseID.getText().toString().trim()).setValue(exercise);
        Toast.makeText(this, "Exercise Added!", Toast.LENGTH_SHORT).show();
    };
    private void clearFields() {
        exerciseTitle.setText("");
        exerciseID.setText("");
        exerciseDescription.setText("");
        exerciseSubTitle.setText("");
    };
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
            assert data != null;
            imgUri = data.getData();
            uploadImg();
        }
    };

    private String getFileExtension (Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    };
    private void uploadImg() {
        ProgressDialog uploadProg = new ProgressDialog(this);
        uploadProg.setMessage("Uploading");
        uploadProg.show();

        if(imgUri != null){
            exerciseImgRef = FirebaseStorage.getInstance().getReference().child("ExerciseImages").child(System.currentTimeMillis()+"."+getFileExtension(imgUri));

            exerciseImgRef.putFile(imgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    exerciseImgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imgUrl = uri.toString();
                            uploadProg.dismiss();
                            Toast.makeText(getApplicationContext(),"Image Upload Successfully!" , Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}