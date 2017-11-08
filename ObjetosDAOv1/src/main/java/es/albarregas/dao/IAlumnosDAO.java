package es.albarregas.dao;

import es.albarregas.beans.Alumno;
import java.sql.Connection;
import java.util.ArrayList;


public interface IAlumnosDAO {
    
    public ArrayList<Alumno> getAlumnos(String where);
    public ArrayList<Alumno> getAlumnosEquipo(String where);
    public void closeConnection(Connection conexion);
    
}
