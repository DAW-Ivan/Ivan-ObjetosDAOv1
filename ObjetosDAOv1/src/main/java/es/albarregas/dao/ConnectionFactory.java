package es.albarregas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/pruebasjava";
    private static final String DB_USER = "java2018";
    private static final String DB_PASS = "2018";
    
    private static Connection conexion = null;
    
    public static Connection getConnection(){
        if(conexion == null){
            try {
                Class.forName(JDBC_DRIVER);
                conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            } catch(ClassNotFoundException e){
                System.out.println("Driver no encontrado");
            } catch(SQLException e){
                System.out.println("Error en la conexión");
                e.printStackTrace();
            }
        }
        return conexion;
    }
    
    public static void closeConnection(Connection conexion){
        try {
            conexion.close();
            conexion=null;
        } catch(SQLException e){
            System.out.println("Error al cerrar la conexión a la BD");
            e.printStackTrace();
        }
        
    }
    
}
