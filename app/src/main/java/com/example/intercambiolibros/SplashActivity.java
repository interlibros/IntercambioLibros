package com.example.intercambiolibros;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //quitar ActionBar
        // getSupportActionBar().hide();


        //user y pass
        //admin22@gmail.com
        //admin22
        //esta quitado para que recoja el usuario, cambia la linea 37 por 38 y no pide login una vez logueado


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                boolean sesion = preferences.getBoolean("sesion", false);

                Intent i;
                if (sesion) {
                    i = new Intent(getApplicationContext(), DashBoardActivity.class);
                    //   i = new Intent(getApplicationContext(), LoginActivity.class);
                } else {

                    i = new Intent(getApplicationContext(), LoginActivity.class);

                }
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}