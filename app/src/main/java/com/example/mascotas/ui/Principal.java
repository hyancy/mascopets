package com.example.mascotas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mascotas.R;
import com.example.mascotas.ui.RegistrarMascotas;
import com.example.mascotas.ui.VisualizarMascotas;

import java.io.File;
import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    Button btnRegistrarMascotas, btnMostrarMascotas, btnEliminarData, btnCerrarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        initComponents();
        listeners();
    }

    private void initComponents() {
        btnMostrarMascotas = findViewById(R.id.btn_mostrar_mascotas);
        btnRegistrarMascotas = findViewById(R.id.btn_registrar_mascotas);
        btnEliminarData = findViewById(R.id.btn_eliminar_data);
        btnCerrarSesion = findViewById(R.id.btn_cerrar_sesion);
    }

    private void listeners() {
        btnRegistrarMascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        btnMostrarMascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });

        btnEliminarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarArchivoDeData();
            }
        });
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(2));
            }
        });
    }

    private ArrayList<Intent> initIntents(){
        ArrayList<Intent> intentList = new ArrayList<>();
        Intent intentRegistro = new Intent(getBaseContext(), RegistrarMascotas.class);
        Intent intentConsultar= new Intent(getBaseContext(), VisualizarMascotas.class);
        Intent intenCerrarSesion = new Intent(getApplicationContext(), MainActivity.class);

        intentList.add(intentRegistro);
        intentList.add(intentConsultar);
        intentList.add(intenCerrarSesion);

        return intentList;
    }

    private void eliminarArchivoDeData() {
        File archivo = new File(getFilesDir(), "registroMascotas.txt");
        if (archivo.exists()){
            archivo.delete();
            Toast.makeText(getApplicationContext(), "Archivo de registro de mascotas eliminado!!!", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(), "No hay registros de mascotas", Toast.LENGTH_LONG).show();
        }
    }
}