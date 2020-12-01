package com.project.usmansh.firstactivity.FireBaseWorking;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.project.usmansh.firstactivity.Model.Student;
import com.project.usmansh.firstactivity.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class FireBaseMainActivity extends AppCompatActivity {

    DatabaseReference myRef;
    ArrayList<Student> studentsList = new ArrayList<>();

     StorageReference storageRef;
     Button pickImageBt;
    ImageView user_image_Iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_main);

    
        pickImageBt = findViewById(R.id.pickImageBt);
        storageRef = FirebaseStorage.getInstance().getReference();
//
       myRef = FirebaseDatabase.getInstance().getReference("Students");
//        addStudentsIntoFireBase();
//        getStudentsList();

        pickImageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImagePicker.Companion.with(FireBaseMainActivity.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){

            Uri imagefile = data.getData();

            uploadFileToFirebase(imagefile);
        }
    }


    private void uploadFileToFirebase(Uri file) {

        Random random = new Random();
        final String randomNumber = String.valueOf(random.nextInt());

        final StorageReference newStorageRef = storageRef.child("image_"+randomNumber+".png");

        //add file on Firebase and got Download Link
        newStorageRef.putFile(file).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()){
                    throw task.getException();
                }
                return newStorageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()){
                    Toast.makeText(FireBaseMainActivity.this, "Uploaded file Success", Toast.LENGTH_SHORT).show();
                    Uri downUri = task.getResult();

                    Student student1 = new Student("Usman", "03224668246", "Krachi");
                    student1.setImageURL(downUri.toString());
                    myRef.child(student1.getName()).setValue(student1);

                    Glide.with(FireBaseMainActivity.this).load(student1.getImageURL()).into(user_image_Iv);


                }
            }
        });


    }

    private void addStudentsIntoFireBase() {


        Student student1 = new Student("Usman", "03224668246", "Krachi");
        Student student2 = new Student("Waleed", "0333526864", "Lahore");
        Student student3 = new Student("Ahmed", "03221548931", "Islamabad");

        myRef.child(student1.getName()).setValue(student1);
        myRef.child(student2.getName()).setValue(student2);
        myRef.child(student3.getName()).setValue(student3);



    }


    private void getStudentsList() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                studentsList.clear();
                for(DataSnapshot studentData:snapshot.getChildren()) {

                    Student student = studentData.getValue(Student.class);
                    System.out.println("Students Data: "+student.getName()+" / "+ student.getPhone()+" / "+student.getAddress());

                    studentsList.add(student);
                }
                Toast.makeText(FireBaseMainActivity.this, "Students Size: " + studentsList.size(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}