//package com.example.progym.admin.exercises;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import com.example.progym.R;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.FirebaseDatabase;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//
//public class excerciseLVadapter extends FirebaseRecyclerAdapter<Exercise,excerciseLVadapter.myviewholder>
//{
//    public excerciseLVadapter(@NonNull FirebaseRecyclerOptions<Exercise> options)
//    {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Exercise model) {
//
//        holder.name.setText(model.getName());
//        holder.course.setText(model.getCourse());
//        holder.email.setText(model.getEmail());
//
//
//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
//                        .setExpanded(true,1100)
//                        .create();
//
//                View myview=dialogPlus.getHolderView();
//                final EditText purl=myview.findViewById(R.id.uimgurl);
//                final EditText name=myview.findViewById(R.id.uname);
//                final EditText course=myview.findViewById(R.id.ucourse);
//                final EditText email=myview.findViewById(R.id.uemail);
//                Button submit=myview.findViewById(R.id.usubmit);
//
//                purl.setText(model.getPurl());
//                name.setText(model.getName());
//                course.setText(model.getCourse());
//                email.setText(model.getEmail());
//
//                dialogPlus.show();
//
//                submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Map<String,Object> map=new HashMap<>();
//                        map.put("purl",purl.getText().toString());
//                        map.put("name",name.getText().toString());
//                        map.put("email",email.getText().toString());
//                        map.put("course",course.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("students")
//                                .child(getRef(position).getKey()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        dialogPlus.dismiss();
//                                    }
//                                });
//                    }
//                });
//
//
//            }
//        });
//
//
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
//                builder.setTitle("Delete Panel");
//                builder.setMessage("Delete...?");
//
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        FirebaseDatabase.getInstance().getReference().child("students")
//                                .child(getRef(position).getKey()).removeValue();
//                    }
//                });
//
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//
//                builder.show();
//            }
//        });
//
//    } // End of OnBindViewMethod
//
//    @NonNull
//    @Override
//    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
//    {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_list_view,parent,false);
//        return new myviewholder(view);
//    }
//
//
//    static class myviewholder extends RecyclerView.ViewHolder
//    {
//        ImageView edit,delete;
//        TextView title,subTitle;
//        public myviewholder(@NonNull View itemView)
//        {
//            super(itemView);
//            title=(TextView)itemView.findViewById(R.id.exNameTV);
//            subTitle=(TextView)itemView.findViewById(R.id.exSNameTV);
//            edit=(ImageView)itemView.findViewById(R.id.editIcon);
//            delete=(ImageView)itemView.findViewById(R.id.deleteIcon);
//        }
//    }
//}
