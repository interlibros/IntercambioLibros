package com.example.intercambiolibros.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intercambiolibros.R;
import com.example.intercambiolibros.fragments.ChatFragment;
import com.example.intercambiolibros.fragments.PerfilFragment;
import com.example.intercambiolibros.fragments.SecondFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, chatFragment).commit();
                        return true;
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, secondFragment).commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, perfilFragment).commit();
                        return true;


                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

    }

    ChatFragment chatFragment = new ChatFragment();
    SecondFragment secondFragment = new SecondFragment();
    PerfilFragment perfilFragment = new PerfilFragment();


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}