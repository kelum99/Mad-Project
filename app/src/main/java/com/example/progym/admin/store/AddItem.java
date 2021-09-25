package com.example.progym.admin.store;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progym.R;
import com.example.progym.admin.exercises.Exercise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddItem extends AppCompatActivity {

    private EditText itemTitle;
    private EditText itemPrice;
    private EditText itemDescription;

    // ImageView preview;
    String imgUrl;
    Button addItem;
    Button uploadBtn;
    Store store;
    DatabaseReference proGym;
    StorageReference itemImgRef;
    public Uri imgUri;
    private static final int IMAGE_REQUEST = 2;

    public AddItem() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        itemTitle = findViewById(R.id.addItem_topic);
        itemPrice = findViewById(R.id.addItem_price);
        itemDescription = findViewById(R.id.addItem_description);
        addItem = findViewById(R.id.add_item_btn);
        uploadBtn = findViewById(R.id.additem_image_btn);
        // preview = findViewById(R.id.exerciseImgUp);

        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Store");

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItem();
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

    private void insertItem() {
        store = new Store();

        store.setItem_title(itemTitle.getText().toString().trim());
        store.setItem_price(itemPrice.getText().toString().trim());
        store.setItem_description(itemDescription.getText().toString());
        store.setImageURL(imgUrl);

        proGym.child(itemTitle.getText().toString().trim()).setValue(store);
        Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();
    }
    private void clearFields() {
        itemTitle.setText("");
        itemPrice.setText("");
        itemDescription.setText("");
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
            imgUri = data.getData();
            uploadImg();
        }
    }

    private String getFileExtension (Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImg() {
        ProgressDialog uploadProg = new ProgressDialog(this);
        uploadProg.setMessage("Uploading");
        uploadProg.show();

        if(imgUri != null){
            itemImgRef = FirebaseStorage.getInstance().getReference().child("ItemImages").child(System.currentTimeMillis()+"."+getFileExtension(imgUri));

            itemImgRef.putFile(imgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    itemImgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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