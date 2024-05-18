package com.iud.rh.iud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/db-rh-iud";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Abigail70";
    
    public static Connection getConnection() throws SQLException{
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    
}
