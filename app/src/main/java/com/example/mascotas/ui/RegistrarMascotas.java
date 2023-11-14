package com.example.mascotas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mascotas.R;
import com.example.mascotas.modelos.ModelMascotas;
import com.example.mascotas.modelos.Registro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class RegistrarMascotas extends AppCompatActivity {
    EditText edtID, edtRaza, edtNombre, edtEdad;
    Button btnRegistrar;

    Registro datosMascotas = new Registro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascotas);

        initComponentes();
        registrarMascotas();
    }
    private void initComponentes() {
        edtID = findViewById(R.id.edt_id);
        edtRaza = findViewById(R.id.edt_raza);
        edtNombre = findViewById(R.id.edt_nombre);
        edtEdad = findViewById(R.id.edt_edad);
        btnRegistrar = findViewById(R.id.But_registro);

    }

    private void registrarMascotas() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelMascotas mascota = new ModelMascotas(edtID.getText().toString(), edtRaza.getText().toString(), edtNombre.getText().toString(), Integer.parseInt(edtEdad.getText().toString()));
                datosMascotas.setAgregarMascotas(mascota);
                limpiarDatos();
                guardarDatosMascota(mascota);
                Toast.makeText(getApplicationContext(), mascota.getNombre()+ " registrado(a) con éxito!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void guardarDatosMascota(ModelMascotas mascota) {
        String datosNuevaMascota = (mascota.getID() +"," + mascota.getRaza() +"," + mascota.getNombre()
                +"," + mascota.getEdad()+ "\n");
        File archivo = new File(getFilesDir(),"registroMascotas.txt");
        if (!archivo.exists()){
            try {
                FileWriter escritorArchivo = new FileWriter(archivo);
                escritorArchivo.append(datosNuevaMascota);
                escritorArchivo.flush();
                escritorArchivo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                /*BufferedWriter bufferedEscritorArchivo = new BufferedWriter(new FileWriter(archivo));
                bufferedEscritorArchivo.write(datosNuevaMascota);
                bufferedEscritorArchivo.close();*/
                Files.write(Paths.get(getFilesDir() +"/registroMascotas.txt"), datosNuevaMascota.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void limpiarDatos() {
        edtID.setText("");
        edtRaza.setText("");
        edtNombre.setText("");
        edtEdad.setText("");
    }
}