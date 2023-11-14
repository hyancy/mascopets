package com.example.mascotas.modelos;

public class ModelMascotas {
    private final String ID;
    private String raza;
    private String nombre;
    private int edad;

    public ModelMascotas(String ID, String raza, String nombre, int edad) {
        this.ID=ID;
        this.raza = raza;
        this.nombre = nombre;
        this.edad = edad;

    }

    public ModelMascotas(String ID) {
        this.ID=ID;
    }

    public String getID() {
        return ID;
    }

    public String getRaza() {
        return raza;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return
                "Id='" + ID + '\'' +
                "Raza='" + raza + '\'' +
                "Nombre='" + nombre + '\'' +
                "Edad=" + edad ;
    }
}
