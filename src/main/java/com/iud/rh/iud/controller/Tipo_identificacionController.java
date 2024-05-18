package com.iud.rh.iud.controller;

import com.iud.rh.iud.dao.Tipo_IdentificacionDao;
import com.iud.rh.iud.domain.Tipo_identificacion;
import java.sql.SQLException;
import java.util.List;

public class Tipo_identificacionController {
    
    private Tipo_IdentificacionDao tipo_IdentificacionDao;
    
    public Tipo_identificacionController(){
        tipo_IdentificacionDao = new Tipo_IdentificacionDao();
    }
    
    public List<Tipo_identificacion> obtenerTipo_identificaciones() throws SQLException{
        return tipo_IdentificacionDao.traerTipoIdentificaciones();
    }
    
    
}
