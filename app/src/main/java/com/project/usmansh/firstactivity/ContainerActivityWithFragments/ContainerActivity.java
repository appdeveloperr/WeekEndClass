package com.project.usmansh.firstactivity.ContainerActivityWithFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.usmansh.firstactivity.R;

public class ContainerActivity extends AppCompatActivity {


        BottomNavigationView bottomNavigationView;
        NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);


        setUpNavigation();
    }


    public void setUpNavigation(){
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        navHostFragment =  (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView,navHostFragment.getNavController());
    }
}