package com.project.usmansh.firstactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.project.usmansh.firstactivity.Adapter.StudentAdapter;
import com.project.usmansh.firstactivity.Model.Student;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {


//    ArrayAdapter<String> arrayAdapter;
//    ArrayList<String> namesList;


    ListView studentListView,studentListViewTwo;
    ArrayList<Student> studentsList = new ArrayList<>();
    StudentAdapter studentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);



        studentListView = findViewById(R.id.studentListView);
        studentListViewTwo = findViewById(R.id.studentListView2);

        Student s1 = new Student("Waleed","03223123123","Lahore");
        Student s2 = new Student("Bilal","21312312124","Karachi");
        Student s3 = new Student("Ahmed","412312324124","Islamabad");
        Student s4 = new Student("Usman","555551231","Multan");
        Student s5 = new Student("Fahad","123125155","KPK");
        Student s6 = new Student("Waleed","03223123123","Lahore");
        Student s7 = new Student("Bilal","21312312124","Karachi");
        Student s8 = new Student("Ahmed","412312324124","Islamabad");
        Student s9 = new Student("Usman","555551231","Multan");
        Student s10 = new Student("Fahad","123125155","KPK");

        studentsList.add(s1);
        studentsList.add(s2);
        studentsList.add(s3);
        studentsList.add(s4);
        studentsList.add(s5);
        studentsList.add(s6);
        studentsList.add(s7);
        studentsList.add(s8);
        studentsList.add(s9);
        studentsList.add(s10);


        studentAdapter = new StudentAdapter(StudentListActivity.this,studentsList);

        studentListView.setAdapter(studentAdapter);
        studentListViewTwo.setAdapter(studentAdapter);


        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Student student = studentsList.get(position);

                Intent intent = new Intent(StudentListActivity.this,StudentDetail.class);
                intent.putExtra("name", student.getName());
                intent.putExtra("phone", student.getPhone());
                intent.putExtra("address", student.getAddress());
                startActivity(intent);
            }
        });




        studentListViewTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Student student = studentsList.get(position);

                Intent intent = new Intent(StudentListActivity.this,StudentDetail.class);
                intent.putExtra("name", student.getName());
                intent.putExtra("phone", student.getPhone());
                intent.putExtra("address", student.getAddress());
                startActivity(intent);
            }
        });
    }
}