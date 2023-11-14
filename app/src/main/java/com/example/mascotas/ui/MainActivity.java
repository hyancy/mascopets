package com.example.mascotas.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mascotas.R;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnRegistrarUsuario, btnIniciarSesion, btnCerrarApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        listeners();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void listeners() {
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });

        btnCerrarApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    private void initComponents() {
        btnRegistrarUsuario = findViewById(R.id.btn_registrar_usuario);
        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);
        btnCerrarApp = findViewById(R.id.btn_cerrar_aplicacion);
    }

    private ArrayList<Intent> initIntents(){
        ArrayList<Intent> intentList = new ArrayList<>();
        Intent intentRegistroUsuario = new Intent(getBaseContext(), RegistroUsuario.class);
        Intent intentIniciarSesion= new Intent(getBaseContext(), IniciarSesion.class);

        intentList.add(intentRegistroUsuario);
        intentList.add(intentIniciarSesion);

        return intentList;
    }

}