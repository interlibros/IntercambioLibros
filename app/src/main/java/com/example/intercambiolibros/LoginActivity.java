package com.example.intercambiolibros;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private Button acceder;
    private FirebaseAuth mAuth;

    private EditText editCorreo;
    private EditText editPassword;

    private String email = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editCorreo = findViewById(R.id.editCorreo);
        editPassword = findViewById(R.id.editPassword);

        acceder = findViewById(R.id.btnLogin);

        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = editCorreo.getText().toString();
                password = editPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {

                    loginUser();

                } else {

                    Toast.makeText(LoginActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void loginUser() {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                    guardarSharedPreferences();
                    finish();
                } else {

                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesion, compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void guardarSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);

        editor.putString("password", password);
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void getPreferences() {

        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        editCorreo.setText(preferences.getString("email", ""));
        editPassword.setText(preferences.getString("password", ""));
    }
}