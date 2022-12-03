package com.example.intercambiolibros;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.io.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;

public class DashBoardActivity extends AppCompatActivity implements NavigationBarView.OnItemReselectedListener {

        BottomNavigationView bottomNavigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.person:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment  , firstFragment).commit();
                        return true;
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment  , secondFragment).commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment  , thirdFragment).commit();
                        return true;


                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home );

    }
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();




    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}