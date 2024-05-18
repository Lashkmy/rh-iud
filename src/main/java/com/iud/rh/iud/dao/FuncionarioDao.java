package com.iud.rh.iud.dao;

import com.iud.rh.iud.domain.Funcionario;
import com.iud.rh.iud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
    
    private static final String TRAER_FUNCIONARIOS = "SELECT f.*,i.nombre nombretipide,e.nombre nombreestciv FROM tb_funcionarios f INNER JOIN tb_tipo_identificaciones i ON f.tipo_identificacion_id = i.id INNER JOIN tb_estados_civiles e ON f.estado_civil_id = e.id";
    
    private static final String CREAR_FUNCIONARIO = "INSERT INTO tb_funcionarios(tipo_identificacion_id, identificacion, nombres, apellidos, estado_civil_id, sexo, direccion, telefono, fecha_nacimiento) VALUES(?::integer,?,?,?,?::integer,?,?,?,?::date)";
    private static final String TRAER_FUNCIONARIO_POR_ID = "SELECT * FROM tb_funcionarios WHERE id = ?";
    private static final String ACTUALIZAR_FUNCIONARIO = "UPDATE tb_funcionarios SET tipo_identificacion_id = ?::integer, identificacion = ?, nombres = ?, apellidos = ?, estado_civil_id = ?::integer, sexo = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?::date WHERE id = ?";
    private static final String BORRAR_FUNCIONARIO = "DELETE from tb_funcionarios WHERE id = ?";
    
    public List<Funcionario> obtenerFuncionarios() throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resulSet = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(TRAER_FUNCIONARIOS);
            resulSet = preparedStatement.executeQuery();
            while (resulSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resulSet.getInt("id"));
                funcionario.setTipo_identificacion_id(resulSet.getInt("tipo_identificacion_id"));               
                funcionario.setIdentificacion(resulSet.getString("identificacion"));
                funcionario.setNombres(resulSet.getString("nombres"));
                funcionario.setApellidos(resulSet.getString("apellidos"));
                funcionario.setEstado_civil_id(resulSet.getInt("estado_civil_id"));
                funcionario.setSexo(resulSet.getString("sexo"));
                funcionario.setDireccion(resulSet.getString("direccion"));
                funcionario.setTelefono(resulSet.getString("telefono"));
                funcionario.setFecha_nacimiento(resulSet.getDate("fecha_nacimiento"));
                funcionario.setNombretipide(resulSet.getString("nombretipide"));
                funcionario.setNombreestciv(resulSet.getString("nombreestciv"));
                funcionarios.add(funcionario);

            }
            return funcionarios;
            
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
     
    public void crearFuncionario(Funcionario funcionario) throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(CREAR_FUNCIONARIO);
                
                preparedStatement.setString(1, Integer.toString(funcionario.getTipo_identificacion_id()));
                preparedStatement.setString(2, funcionario.getIdentificacion());
                preparedStatement.setString(3, funcionario.getNombres());
                preparedStatement.setString(4, funcionario.getApellidos());
                preparedStatement.setString(5, Integer.toString(funcionario.getEstado_civil_id()));
                preparedStatement.setString(6, funcionario.getSexo());
                preparedStatement.setString(7, funcionario.getDireccion());
                preparedStatement.setString(8, funcionario.getTelefono());
                preparedStatement.setString(9, dateFormat.format(funcionario.getFecha_nacimiento()));
                
                preparedStatement.executeUpdate();
 
        } finally {
            
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }

        }
        
    }    
    
    public Funcionario obtenerFuncionario(int id) throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resulSet = null;
        Funcionario funcionario = null;
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(TRAER_FUNCIONARIO_POR_ID);
            preparedStatement.setInt(1, id);
            resulSet = preparedStatement.executeQuery();
            if (resulSet.next()) {
                funcionario.setId(resulSet.getInt("id"));
                funcionario.setTipo_identificacion_id(resulSet.getInt("tipo_identificacion_id"));               
                funcionario.setIdentificacion(resulSet.getString("identificacion"));
                funcionario.setNombres(resulSet.getString("nombres"));
                funcionario.setApellidos(resulSet.getString("apellidos"));
                funcionario.setEstado_civil_id(resulSet.getInt("estado_civil_id"));
                funcionario.setSexo(resulSet.getString("sexo"));
                funcionario.setDireccion(resulSet.getString("direccion"));
                funcionario.setTelefono(resulSet.getString("telefono"));
                funcionario.setIdentificacion(resulSet.getString("fecha_nacimiento"));
                
            }
            return funcionario;
            
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
    
    public void actualizarFuncionario(int id, Funcionario funcionario) throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(ACTUALIZAR_FUNCIONARIO);
                
                preparedStatement.setString(1, Integer.toString(funcionario.getTipo_identificacion_id()));
                preparedStatement.setString(2, funcionario.getIdentificacion());
                preparedStatement.setString(3, funcionario.getNombres());
                preparedStatement.setString(4, funcionario.getApellidos());
                preparedStatement.setString(5, Integer.toString(funcionario.getEstado_civil_id()));
                preparedStatement.setString(6, funcionario.getSexo());
                preparedStatement.setString(7, funcionario.getDireccion());
                preparedStatement.setString(8, funcionario.getTelefono());
                preparedStatement.setString(9, dateFormat.format(funcionario.getFecha_nacimiento()));
                preparedStatement.setInt(10, id);
                
                preparedStatement.executeUpdate();
 
        } finally {
            
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }

        }
        
    }
    
    public void eliminarFuncionario(int id) throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(BORRAR_FUNCIONARIO);                
                preparedStatement.setInt(1, id);               
                preparedStatement.executeUpdate();
 
        } finally {
            
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }

        }
        
    }      
    
}
