package com.project.usmansh.firstactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class StudentDetail extends AppCompatActivity {


    TextView studentName, studentPhone, studentAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);


        studentName = findViewById(R.id.studentName);
        studentPhone = findViewById(R.id.studentPhone);
        studentAddress = findViewById(R.id.studentAddress);

        studentName.setText(getIntent().getStringExtra("name"));
        studentPhone.setText(getIntent().getStringExtra("phone"));
        studentAddress.setText(getIntent().getStringExtra("address"));

    }
}