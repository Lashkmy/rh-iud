package com.iud.rh.iud.domain;

public class Estado_civil {
   
    private int id;
    private String nombre;

    public Estado_civil() {
    }

    public Estado_civil(int id, String nombre) {
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
