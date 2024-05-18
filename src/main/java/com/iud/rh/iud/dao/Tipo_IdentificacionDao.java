package com.iud.rh.iud.dao;

import com.iud.rh.iud.domain.Tipo_identificacion;
import com.iud.rh.iud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tipo_IdentificacionDao {
    
    private static final String TRAER_TIPO_IDENTIFICACIONES = "SELECT * from tb_tipo_identificaciones";
    
    public List<Tipo_identificacion> traerTipoIdentificaciones() throws SQLException{
 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resulSet = null; 
        List<Tipo_identificacion> tipoIdentificaciones = new ArrayList<>();
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(TRAER_TIPO_IDENTIFICACIONES);
            resulSet = preparedStatement.executeQuery();  

            while (resulSet.next()) {
                Tipo_identificacion tipoIdentificacion = new Tipo_identificacion();
                tipoIdentificacion.setId(resulSet.getInt("id"));             
                tipoIdentificacion.setNombre(resulSet.getString("nombre"));
                tipoIdentificaciones.add(tipoIdentificacion);

            }            
            return tipoIdentificaciones;        
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
