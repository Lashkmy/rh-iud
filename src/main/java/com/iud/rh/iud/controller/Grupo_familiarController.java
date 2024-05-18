package com.iud.rh.iud.controller;

import com.iud.rh.iud.dao.Grupo_familiarDao;
import com.iud.rh.iud.domain.Grupo_familiar;
import java.sql.SQLException;
import java.util.List;

public class Grupo_familiarController {
    
    private Grupo_familiarDao grupo_FamiliarDao;
    
    public Grupo_familiarController(){
        grupo_FamiliarDao = new Grupo_familiarDao();
    }
    
    public List<Grupo_familiar> obtenerGrupo_familiares(int id) throws SQLException{
        return grupo_FamiliarDao.traerGrupoFamiliares(id);
    }
    
    
}
