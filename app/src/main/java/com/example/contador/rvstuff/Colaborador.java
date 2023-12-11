package com.example.contador.rvstuff;

public class Colaborador {
    private final String nombre, direccion;
    private final int imagen;

    public Colaborador(String nombre, String direccion, int imagen) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getImagen() {
        return imagen;
    }
}
