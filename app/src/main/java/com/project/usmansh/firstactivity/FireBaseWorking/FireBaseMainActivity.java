package com.project.usmansh.firstactivity.FireBaseWorking;

import androidx.annotation.NonNull;
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.usmansh.firstactivity.Model.Student;
import com.project.usmansh.firstactivity.R;

public class FireBaseMainActivity extends AppCompatActivity {

    DatabaseReference myRef;

=======
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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
>>>>>>> 7c228f1933f669748e30384f9625b51b5c4ef2ae

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_main);

<<<<<<< HEAD

        myRef = FirebaseDatabase.getInstance().getReference("i");

        Student student1 = new Student("BawaG1", "112233", "Krachi");
        Student student2 = new Student("BawaG2", "112233", "Krachi");
        Student student3 = new Student("BawaG3", "112233", "Krachi");


//        myRef.child("S1").setValue(student1).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(FireBaseMainActivity.this, "Data Stored Success!", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(FireBaseMainActivity.this, "Data Did not store!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        myRef.child(student1.getName()).removeValue();
        myRef.child(student2.getName()).setValue(student2);
        myRef.child(student3.getName()).setValue(student3);

        System.out.println("Value: "+myRef);
    }
=======
        pickImageBt = findViewById(R.id.pickImageBt);
        storageRef = FirebaseStorage.getInstance().getReference();
//
//        myRef = FirebaseDatabase.getInstance().getReference("Students");
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
        String randomNumber = String.valueOf(random.nextInt());

        StorageReference newStorageRef = storageRef.child("image_"+randomNumber+".png");

        newStorageRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(FireBaseMainActivity.this, "Uploaded file Success", Toast.LENGTH_SHORT).show();
//                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(FireBaseMainActivity.this, "Error File uploading", Toast.LENGTH_SHORT).show();
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




>>>>>>> 7c228f1933f669748e30384f9625b51b5c4ef2ae
}