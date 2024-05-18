package com.iud.rh.iud.controller;

import com.iud.rh.iud.dao.Estado_CivilDao;
import com.iud.rh.iud.domain.Estado_civil;
import java.sql.SQLException;
import java.util.List;

public class Estado_civilController {
    
    private Estado_CivilDao estado_CivilDao;
    
    public Estado_civilController(){
        estado_CivilDao = new Estado_CivilDao();
    }
    
    public List<Estado_civil> obtenerEstado_civiles() throws SQLException{
        return estado_CivilDao.traerEstadoCiviles();
    }
    
    
}
