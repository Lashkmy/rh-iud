package com.iud.rh.iud.dao;

import com.iud.rh.iud.domain.Grupo_familiar;
import com.iud.rh.iud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Grupo_familiarDao {
    
    private static final String TRAER_GRUPO_FAMILIARES = "SELECT f.*,i.nombre nombretipide,e.nombre nombreparentesco FROM tb_grupos_familiares f INNER JOIN tb_tipo_identificaciones i ON f.tipo_identificacion_id = i.id INNER JOIN tb_parentescos e ON f.parentesco_id = e.id WHERE f.funcionario_id = ?";
    
    public List<Grupo_familiar> traerGrupoFamiliares(int id) throws SQLException{
 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resulSet = null; 
        List<Grupo_familiar> grupoFamiliares = new ArrayList<>();
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(TRAER_GRUPO_FAMILIARES);
            preparedStatement.setInt(1, id);
            resulSet = preparedStatement.executeQuery();  

            while (resulSet.next()) {
                Grupo_familiar grupoFamiliar = new Grupo_familiar();
                grupoFamiliar.setId(resulSet.getInt("id"));             
                grupoFamiliar.setNombretipide(resulSet.getString("nombretipide"));
                grupoFamiliar.setNombre_completo(resulSet.getString("nombre_completo"));
                grupoFamiliar.setNombreparent(resulSet.getString("nombreparentesco"));
                grupoFamiliar.setIdentificacion(resulSet.getString("identificacion"));
                grupoFamiliar.setDireccion(resulSet.getString("direccion"));
                grupoFamiliar.setTelefono(resulSet.getString("telefono"));
                grupoFamiliares.add(grupoFamiliar);

            }            
            return grupoFamiliares;        
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
