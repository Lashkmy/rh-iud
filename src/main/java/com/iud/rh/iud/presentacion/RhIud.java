package com.iud.rh.iud.presentacion;

import com.iud.rh.iud.controller.FuncionarioController;
import com.iud.rh.iud.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RhIud {
    
    
    public static void obtenerFuncionarios(FuncionarioController funcionarioController){
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            
            if (funcionarios.isEmpty()){
                System.out.println("No hay funcionarios.");
            } else {
               funcionarios.forEach(funcionario ->{
                   System.out.println("id "+funcionario.getNombres());
               });
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        FuncionarioController funcionarioController = new FuncionarioController();
        obtenerFuncionarios(funcionarioController);
    }
}
