//package com.example.progym.admin.Event;
//
//import android.app.ProgressDialog;
//import android.content.ContentResolver;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.webkit.MimeTypeMap;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.progym.R;
//import com.example.progym.admin.exercises.Exercise;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//public class CreateEvent extends AppCompatActivity {
//
//    private EditText eventType;
//    private EditText eventDescription;
//    private EditText eventDate;
//    private EditText eventTime;
//    // ImageView preview;
//    String imgUrl;
//    Button createEvent;
//    Button uploadBtn;
//    Event event;
//    DatabaseReference proGym;
//    StorageReference exerciseImgRef;
//    public Uri imgUri;
//    private static final int IMAGE_REQUEST = 2;
//
//    public CreateEvent() {
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_event);
//
//        eventType = findViewById(R.id.eventTypeTxt);
//        eventDescription = findViewById(R.id.eventDescriptionTxt);
//        eventDate = findViewById(R.id.eventDateTxt);
//        eventTime findViewById(R.id.eventTimeTxt);
//        createEvent= findViewById(R.id.createEvent);
//        uploadBtn = findViewById(R.id.uploadBtn1);
//        // preview = findViewById(R.id.exerciseImgUp);
//
//        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Event");
//
//        createEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                insertEvent();
//                clearFields();
//            }
//        });
//
//        uploadBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chooseImage();
//            }
//        });
//
//
//
//    }
//    private void insertEvent() {
//        event = new Event();
//
//        event.setEventType(eventType.getText().toString().trim());
//        event.setEventDescription(eventDescription.getText().toString().trim());
//        event.setDate(eventDate .getText().toString());
//        event.setTime(eventTime .getText().toString());
//        event.setImageURL(imgUrl);
//
//        Task<Void> voidTask = proGym.push().setValue(event);
//        Toast.makeText(this, "Event Added!", Toast.LENGTH_SHORT).show();
//    }
//    private void clearFields() {
//        eventType.setText("");
//        eventDescription.setText("");
//        eventDate.setText("");
//        eventTime.setText("");
//    }
//    private void chooseImage() {
//        Intent intent = new Intent();
//        intent.setType("image/");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
//            imgUri = data.getData();
//            uploadImg();
//        }
//    }
//
//    private String getFileExtension (Uri uri){
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//    private void uploadImg() {
//        ProgressDialog uploadProg = new ProgressDialog(this);
//        uploadProg.setMessage("Uploading");
//        uploadProg.show();
//
//        if(imgUri != null){
//            eventImgRef = FirebaseStorage.getInstance().getReference().child("ExerciseImages").child(System.currentTimeMillis()+"."+getFileExtension(imgUri));
//
//            eventImgRef.putFile(imgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                    eventImgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            imgUrl = uri.toString();
//                            uploadProg.dismiss();
//                            Toast.makeText(getApplicationContext(),"Image Upload Successfully!" , Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            });
//        }
//    }
//}
