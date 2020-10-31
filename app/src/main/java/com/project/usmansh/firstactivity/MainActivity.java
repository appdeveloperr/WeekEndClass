package com.project.usmansh.firstactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.usmansh.firstactivity.Model.Student;
import com.project.usmansh.firstactivity.Utils.TinyDB;

public class MainActivity extends AppCompatActivity {

    RelativeLayout seeInfo_Rl;
    EditText aceessCode_Ed;
    public static String PREF_FILE_NAME = "myAppPref";
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seeInfo_Rl  = findViewById(R.id.seeInfo_Rl);
        aceessCode_Ed  = findViewById(R.id.aceessCode_Ed);
        tinyDB = new TinyDB(this);


             saveDataIntoTinyDB();
            checkUserIsLoginOrNot();


//        getDataFromSharedPref();

        seeInfo_Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                saveDataIntoTinyDB();
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

    }




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