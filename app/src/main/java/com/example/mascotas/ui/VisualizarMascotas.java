package com.example.mascotas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mascotas.R;
import com.example.mascotas.modelos.ModelMascotas;
import com.example.mascotas.modelos.Registro;
import com.example.mascotas.ui.recyclerView.AdapterMascotas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class VisualizarMascotas extends AppCompatActivity {

    Button btnMostrar;
    TextView tvNoHayMascotas;
    RecyclerView recyclerViewMascotas;
    ArrayList<ModelMascotas> listaMascotas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_mascotas);

        initComponentes();
        consultarMascotas();
        mostrarMascotas();
    }


    private void mostrarMascotas() {
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaMascotas.size() <=0){
                    Intent intentRegistrarMascota = new Intent(getApplicationContext(), RegistrarMascotas.class);
                    startActivity(intentRegistrarMascota);
                }else{
                    consultarMascotas();
                }
            }
        });
    }

    private void initComponentes() {
        btnMostrar = findViewById(R.id.but_mostrar_mascota);
        recyclerViewMascotas = findViewById(R.id.recycler_view_mascotas);
        tvNoHayMascotas = findViewById(R.id.text_view_no_hay_mascotas);
    }

    private void consultarMascotas() {
        File archivo = new File(getFilesDir(), "registroMascotas.txt");

        if (archivo.exists()) {
            try {
                FileReader lectorArchivo = new FileReader(archivo);
                BufferedReader bufferedLectorArchivo = new BufferedReader(lectorArchivo);
                String linea;
                //ArrayList<ModelMascotas> listaMascotasRecuperadas = new ArrayList<>();
                listaMascotas.clear();
                while ((linea = bufferedLectorArchivo.readLine()) != null) {
                    String registroMascotaRecuperada[] = linea.split(",");

                    ModelMascotas mascotaRecuperada = new ModelMascotas(
                            registroMascotaRecuperada[0].toString(),
                            registroMascotaRecuperada[1].toString(),
                            registroMascotaRecuperada[2].toString(),
                            Integer.parseInt(registroMascotaRecuperada[3].toString()));
                    //listaMascotasRecuperadas.add(mascotaRecuperada);
                    listaMascotas.add(mascotaRecuperada);
                }

                bufferedLectorArchivo.close();
                lectorArchivo.close();
                visualizarMascotas(listaMascotas);
                //visualizarMascotas(listaMascotasRecuperadas);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            visualizarMascotas(listaMascotas);
            Toast.makeText(getApplicationContext(), "No existe data de mascotas", Toast.LENGTH_LONG).show();
        }

    }

    private void visualizarMascotas(ArrayList<ModelMascotas> listaMascotasRecuperadas) {
        if (getRotation(getApplicationContext()).equals("horizontal")) {
            if (listaMascotasRecuperadas.size() > 0) {
                recyclerViewMascotas.setVisibility(View.VISIBLE);
                tvNoHayMascotas.setVisibility(View.GONE);
                recyclerViewMascotas.setLayoutManager(new GridLayoutManager(this, 2));
                recyclerViewMascotas.setHasFixedSize(true);
                AdapterMascotas adapterListaMascotas = new AdapterMascotas(listaMascotasRecuperadas);
                recyclerViewMascotas.setAdapter(adapterListaMascotas);
            } else {
                btnMostrar.setText("Registrar una mascota");
                tvNoHayMascotas.setText("No se encontraron mascotas registadas");
                recyclerViewMascotas.setVisibility(View.GONE);
                //tvNoHayMascotas.setTextSize(1, 32);
                tvNoHayMascotas.setVisibility(View.VISIBLE);
            }
        } else {
            if (listaMascotasRecuperadas.size() > 0) {
                recyclerViewMascotas.setVisibility(View.VISIBLE);
                tvNoHayMascotas.setVisibility(View.GONE);

                recyclerViewMascotas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                recyclerViewMascotas.setHasFixedSize(true);
                AdapterMascotas adapterListaMascotas = new AdapterMascotas(listaMascotasRecuperadas);
                recyclerViewMascotas.setAdapter(adapterListaMascotas);
            } else {
                btnMostrar.setText("Registrar una mascota");
                tvNoHayMascotas.setText("No se encontraron mascotas registadas");
                recyclerViewMascotas.setVisibility(View.GONE);
                //tvNoHayMascotas.setTextSize(1, 32);
                tvNoHayMascotas.setVisibility(View.VISIBLE);
            }

        }
    }

    public String getRotation(Context context) {
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                return "vertical";
            case Surface.ROTATION_90:
            default:
                return "horizontal";
        }
    }

}