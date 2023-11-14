package com.example.mascotas.modelos;

import java.util.ArrayList;

public class Registro {
    private ArrayList<ModelMascotas>listaMascotas=new ArrayList<>() ;

    public ArrayList<ModelMascotas> getListaMascotas() {
        return listaMascotas;
    }

    public void setAgregarMascotas(ModelMascotas mascota) {
        listaMascotas.add(mascota);
    }
}
