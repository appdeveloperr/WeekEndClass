package com.project.usmansh.firstactivity.FireBaseWorking;

import androidx.annotation.NonNull;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_main);


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
}