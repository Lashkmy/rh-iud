package com.iud.rh.iud.domain;

public class Tipo_identificacion {
    
    private int id;
    private String nombre;

    public Tipo_identificacion() {
    }

    
    public Tipo_identificacion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
