package com.iud.rh.iud.domain;

public class Grupo_familiar {
    
    private int id;
    private String nombre_completo;
    private int parentesco_id;
    private int tipo_identificacion_id;
    private String identificacion;
    private String direccion;
    private String telefono;
    private int funcionario_id;
    private String nombreparent;
    private String nombretipide;
    private String nombrefuncio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public int getParentesco_id() {
        return parentesco_id;
    }

    public void setParentesco_id(int parentesco_id) {
        this.parentesco_id = parentesco_id;
    }

    public int getTipo_identificacion_id() {
        return tipo_identificacion_id;
    }

    public void setTipo_identificacion_id(int tipo_identificacion_id) {
        this.tipo_identificacion_id = tipo_identificacion_id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(int funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public String getNombreparent() {
        return nombreparent;
    }

    public void setNombreparent(String nombreparent) {
        this.nombreparent = nombreparent;
    }

    public String getNombretipide() {
        return nombretipide;
    }

    public void setNombretipide(String nombretipide) {
        this.nombretipide = nombretipide;
    }

    public String getNombrefuncio() {
        return nombrefuncio;
    }

    public void setNombrefuncio(String nombrefuncio) {
        this.nombrefuncio = nombrefuncio;
    }

    @Override
    public String toString() {
        return nombre_completo;
    }
    
}
