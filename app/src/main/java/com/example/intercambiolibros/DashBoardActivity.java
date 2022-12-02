package com.example.intercambiolibros;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DashBoardActivity extends AppCompatActivity {

    RelativeLayout option_registros;
    RelativeLayout option_healtcenters;
    RelativeLayout option_maps;
    RelativeLayout option_notas;
    RelativeLayout option_buscar;
    RelativeLayout option_config;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


//        option_registros = findViewById(R.id.optionRegistros);
//
//        option_registros.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DashBoardActivity.this, DatosActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(i);
//            }
//        });
//
//
//        option_healtcenters = findViewById(R.id.optionHealthCenters);
//
//        option_healtcenters.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DashBoardActivity.this, GraficsActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(i);
//            }
//        });
//
//        option_maps = findViewById(R.id.optionMaps);
//
//        option_maps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DashBoardActivity.this, MapsActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(i);
//            }
//        });
//
//        option_notas = findViewById(R.id.optionNotas);
//
//        option_notas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DashBoardActivity.this, NotasActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(i);
//            }
//        });
//
//        option_buscar = findViewById(R.id.optionBuscar);
//
//        option_buscar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DashBoardActivity.this, BuscarActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(i);
//            }
//        });
//
//        option_config = findViewById(R.id.optionConfig);
//
//        option_config.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DashBoardActivity.this, ConfiguracionActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(i);
//            }
//        });

    }


}