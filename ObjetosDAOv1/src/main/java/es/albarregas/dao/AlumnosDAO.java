package es.albarregas.dao;

import es.albarregas.beans.Alumno;
import es.albarregas.beans.Equipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlumnosDAO implements IAlumnosDAO {

    public ArrayList<Alumno> getAlumnos(String limit) {

        ArrayList<Alumno> lista = new ArrayList();
        String consulta = "SELECT idAlumno,nombre,grupo FROM alumnos " + limit;
        try {
            Connection conexion=ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);

            while (resultado.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(resultado.getInt("idAlumno"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setGrupo(resultado.getString("grupo"));
                lista.add(alumno);
            }
            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Alumno> getAlumnosEquipo(String limit) {

        ArrayList<Alumno> alumnos = getAlumnos(limit);
        ArrayList<Alumno> lista=new ArrayList<Alumno>();
        String consulta = "SELECT * FROM alumnos NATURAL join equipos where idAlumno=?";
        try {
            Connection conexion=ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(consulta);
            for (Alumno a : alumnos) {
                preparada.setInt(1, a.getIdAlumno());
                ResultSet resultado = preparada.executeQuery();
                while (resultado.next()) {
                    Equipo equipo = new Equipo();
                    equipo.setIdEquipo(resultado.getInt("idEquipo"));
                    equipo.setMarca(resultado.getString("marca"));
                    equipo.setNumSerie(resultado.getString("numSerie"));
                    a.setEquipo(equipo);
                    lista.add(a);
                }
                resultado.close();              
            }
            preparada.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();
        }
        return lista;

    }

    public void closeConnection(Connection conexion) {

        ConnectionFactory.closeConnection(conexion);

    }

}
