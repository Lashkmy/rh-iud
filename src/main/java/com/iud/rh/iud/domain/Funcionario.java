package com.iud.rh.iud.domain;

import java.util.Date;

public class Funcionario {
    
    private int id;
    private int tipo_identificacion_id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private int estado_civil_id;
    private String sexo;
    private String direccion;
    private String telefono;
    private Date fecha_nacimiento;
    private String nombretipide;
    private String nombreestciv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEstado_civil_id() {
        return estado_civil_id;
    }

    public void setEstado_civil_id(int estado_civil_id) {
        this.estado_civil_id = estado_civil_id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombretipide() {
        return nombretipide;
    }

    public void setNombretipide(String nombretipide) {
        this.nombretipide = nombretipide;
    }

    public String getNombreestciv() {
        return nombreestciv;
    }

    public void setNombreestciv(String nombreestciv) {
        this.nombreestciv = nombreestciv;
    }

    @Override
    public String toString() {
        return nombretipide +" " +identificacion+" " + nombres+" " + apellidos;
    }
    
}
