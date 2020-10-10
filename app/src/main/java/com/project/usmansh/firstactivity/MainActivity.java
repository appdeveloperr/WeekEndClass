package com.project.usmansh.firstactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RelativeLayout seeInfo_Rl;
    EditText aceessCode_Ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seeInfo_Rl  = findViewById(R.id.seeInfo_Rl);
        aceessCode_Ed  = findViewById(R.id.aceessCode_Ed);


        seeInfo_Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code Here When Button will clicked
                String enteredCode = aceessCode_Ed.getText().toString();
                Toast.makeText(MainActivity.this, enteredCode, Toast.LENGTH_LONG).show();

                Intent goToNextScreen = new Intent(MainActivity.this, StudentListActivity.class);
                startActivity(goToNextScreen);

            }
        });





    }
}