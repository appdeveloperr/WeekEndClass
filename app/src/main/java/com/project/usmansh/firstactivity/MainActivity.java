package com.project.usmansh.firstactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.usmansh.firstactivity.DBClasses.DatabaseManager;
import com.project.usmansh.firstactivity.DBClasses.StudentItemTable;
import com.project.usmansh.firstactivity.DBClasses.TeacherDBManeger;
import com.project.usmansh.firstactivity.DBClasses.TeacherItemTable;
import com.project.usmansh.firstactivity.Model.Student;
import com.project.usmansh.firstactivity.Utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RelativeLayout seeInfo_Rl;
    EditText aceessCode_Ed;
    public static String PREF_FILE_NAME = "myAppPref";
    TinyDB tinyDB;
    SharedPreferences.Editor editor;
    DatabaseManager studentDBManager;
    TeacherDBManeger teacherDBManeger;
    List<StudentItemTable> studentList;
    List<TeacherItemTable> teacherList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seeInfo_Rl  = findViewById(R.id.seeInfo_Rl);
        aceessCode_Ed  = findViewById(R.id.aceessCode_Ed);
        tinyDB = new TinyDB(this);

        studentDBManager = DatabaseManager.getInstance(getApplicationContext());
        teacherDBManeger = TeacherDBManeger.getInstance(getApplicationContext());

//             saveDataIntoTinyDB();
//            checkUserIsLoginOrNot();


//        getDataFromSharedPref();

        seeInfo_Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveStudentsIntoSQLDB();
               // saveDataIntoTinyDB();
               // saveDataIntoSharedPref(aceessCode_Ed.getText().toString());

                //showDialogBox();
                //Code Here When Button will clicked
//                String enteredCode = aceessCode_Ed.getText().toString();
//                Toast.makeText(MainActivity.this, enteredCode, Toast.LENGTH_LONG).show();
//
//                Intent goToNextScreen = new Intent(MainActivity.this, StudentListActivity.class);
//                startActivity(goToNextScreen);

            }
        });

        aceessCode_Ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                studentList = studentDBManager.getAllStudent();
                teacherList = teacherDBManeger.getAllTeacher();

                Toast.makeText(MainActivity.this, "Student Size: "+studentList.get(0).getName()+"\nTeacher Size: "+teacherList.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void saveStudentsIntoSQLDB() {



        //--------------- DataBase Functions -------------------



            SharedPreferences prefs = getSharedPreferences("StudentAppData", MODE_PRIVATE);
            int indexInDb = prefs.getInt("index", 0); //0 is the default value.

            editor = getSharedPreferences("StudentAppData", MODE_PRIVATE).edit();
            String index = String.valueOf(indexInDb+1);
            editor.putInt("index",Integer.parseInt(index));
            editor.apply();

            String name = "Waleed and Ahemd";
            String phone = "032234712903";
            String address = "Lahore";

//            String name = name.getText().toString().trim();
//            String phone = phone.getText().toString().trim();
//            String address = address.getText().toString().trim();



            if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty()) {


                StudentItemTable studentItemTable = new StudentItemTable();
                studentItemTable.setIndex(index);
                studentItemTable.setName(name);
                studentItemTable.setPhone(phone);
                studentItemTable.setAddress(address);

                TeacherItemTable teacherItemTable = new TeacherItemTable();
                teacherItemTable.setIndex(index);
                teacherItemTable.setName(name);
                teacherItemTable.setPhone(phone);
                teacherItemTable.setAddress(address);



                int isSuccess = studentDBManager.insertStudentItem(studentItemTable,false);

                if(isSuccess == 0){
                    Toast.makeText(getApplicationContext(),"Student Saved Success",Toast.LENGTH_SHORT).show();
                }else if(isSuccess == 1){
                    Toast.makeText(getApplicationContext(),"Student with this id exist",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Student adding failed",Toast.LENGTH_SHORT).show();
                }
//

                int isTecSuccess = teacherDBManeger.insertTeacherItem(teacherItemTable,false);

                if(isTecSuccess == 0){
                    Toast.makeText(getApplicationContext(),"Teacher Saved Success",Toast.LENGTH_SHORT).show();
                }else if(isTecSuccess == 1){
                    Toast.makeText(getApplicationContext(),"Teacher with this id exist",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Teacher adding failed",Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "Error: Field is empty!", Toast.LENGTH_SHORT).show();
            }



    }


//    public int addUserToDB(StudentItem stuItemDB){
//        int isSuccess;
//        isSuccess = DatabaseManager.getInstance(getApplicationContext()).insertUserItem(vehItemDB,false);
//
//
//        if(isSuccess == 0){
//            Toast.makeText(getApplicationContext(),"Veh Saved Success",Toast.LENGTH_SHORT).show();
//        }else if(isSuccess == 1){
//            Toast.makeText(getApplicationContext(),"Veh with this id exist",Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(getApplicationContext(),"Veh adding failed",Toast.LENGTH_SHORT).show();
//        }
//        return isSuccess;
//    }
//
//    public void presentToast(){
//        Toast.makeText(getApplicationContext(),"Save Veh",Toast.LENGTH_SHORT).show();
//    }
//
//    public List<VehItemDB> getAllUsers(){
//        return  DatabaseManager.getInstance(getApplicationContext()).getAllUsers();
//    }













    //-------------------------------------------------------------------------

    private void checkUserIsLoginOrNot() {


        Student studentObjectGetFromTinyDB = tinyDB.getObject("user", Student.class);

        if(studentObjectGetFromTinyDB == null){
            //User is not logged in
            Toast.makeText(this, "User is not logged in", Toast.LENGTH_SHORT).show();
        }else{
            //User is login
            Intent goToNextScreen = new Intent(MainActivity.this, StudentListActivity.class);
            startActivity(goToNextScreen);
        }


    }

    private void saveDataIntoTinyDB() {

        Student student = new Student("john", "24","Lahore");
        tinyDB.putObject("user", student); //saves the object

    }


    private void saveDataIntoSharedPref(String code) {

        SharedPreferences.Editor editor = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE).edit();
        editor.putString("code", code);
        editor.apply();
    }


    private void getDataFromSharedPref(){

        SharedPreferences prefs = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        String code = prefs.getString("code", "No Code defined");
        Toast.makeText(this, "Code In SP: "+code, Toast.LENGTH_SHORT).show();
    }



    public void showDialogBox(){

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_testing);

        final TextView textView = dialog.findViewById(R.id.textview);
        Button button = dialog.findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(MainActivity.this, "Text Entered: "+textView.getText(), Toast.LENGTH_SHORT).show();

                dialog.dismiss();
                }
            });



        dialog.show();


    }






    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("OnStart: Calling");


    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume: Calling");

    }


    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("OnPause: Calling");

    }


    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("OnStop: Calling");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("OnDestroy: Calling");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("OnRestart: Calling");

    }
}