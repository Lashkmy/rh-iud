package com.iud.rh.iud.dao;

import com.iud.rh.iud.domain.Estado_civil;
import com.iud.rh.iud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Estado_CivilDao {
    
    private static final String TRAER_ESTADO_CIVILES = "SELECT * from tb_estados_civiles";
    
    public List<Estado_civil> traerEstadoCiviles() throws SQLException{
 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resulSet = null; 
        List<Estado_civil> estadoCiviles = new ArrayList<>();
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(TRAER_ESTADO_CIVILES);
            resulSet = preparedStatement.executeQuery();  

            while (resulSet.next()) {
                Estado_civil estadoCivil = new Estado_civil();
                estadoCivil.setId(resulSet.getInt("id"));             
                estadoCivil.setNombre(resulSet.getString("nombre"));
                estadoCiviles.add(estadoCivil);

            }            
            return estadoCiviles;        
        } finally {
            
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (resulSet != null){
                resulSet.close();
            }
        }        
        
    }
    
}
