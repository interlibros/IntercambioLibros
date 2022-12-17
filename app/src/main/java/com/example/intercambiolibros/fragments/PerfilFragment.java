package com.example.intercambiolibros.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.intercambiolibros.activitys.LoginActivity;
import com.example.intercambiolibros.R;

public class PerfilFragment extends Fragment {

    private Button eliminarLogin;
    public PerfilFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);

        eliminarLogin = rootView.findViewById(R.id.btnEliminarLogin );

        SharedPreferences preferences =getActivity().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        eliminarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editor.clear();

                Intent i;
                i = new Intent(PerfilFragment.this.getActivity() , LoginActivity.class);
                startActivity(i);
                //Toast.makeText(getView().getContext() , "Complete los campos", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView ;
    }
}