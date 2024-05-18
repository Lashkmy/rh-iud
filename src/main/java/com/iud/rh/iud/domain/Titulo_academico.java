package com.iud.rh.iud.domain;

import java.util.Date;

public class Titulo_academico {

    private int id;
    private Date fecha_obtiene;
    private int titulo_id;
    private int universidad_id;
    private int nivel_educativo_id;
    private String nombretitulo;
    private String nombreuniver;
    private String nombrenivedu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_obtiene() {
        return fecha_obtiene;
    }

    public void setFecha_obtiene(Date fecha_obtiene) {
        this.fecha_obtiene = fecha_obtiene;
    }

    public int getTitulo_id() {
        return titulo_id;
    }

    public void setTitulo_id(int titulo_id) {
        this.titulo_id = titulo_id;
    }

    public int getUniversidad_id() {
        return universidad_id;
    }

    public void setUniversidad_id(int universidad_id) {
        this.universidad_id = universidad_id;
    }

    public int getNivel_educativo_id() {
        return nivel_educativo_id;
    }

    public void setNivel_educativo_id(int nivel_educativo_id) {
        this.nivel_educativo_id = nivel_educativo_id;
    }

    public String getNombretitulo() {
        return nombretitulo;
    }

    public void setNombretitulo(String nombretitulo) {
        this.nombretitulo = nombretitulo;
    }

    public String getNombreuniver() {
        return nombreuniver;
    }

    public void setNombreuniver(String nombreuniver) {
        this.nombreuniver = nombreuniver;
    }

    public String getNombrenivedu() {
        return nombrenivedu;
    }

    public void setNombrenivedu(String nombrenivedu) {
        this.nombrenivedu = nombrenivedu;
    }

    @Override
    public String toString() {
        return nombretitulo;
    }
    
}
