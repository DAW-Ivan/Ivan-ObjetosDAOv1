/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Alumno;
import es.albarregas.dao.IAlumnosDAO;
import es.albarregas.dao.AlumnosDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Control"})
public class Controlador extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cantidad = request.getParameter("numero");
        String clausulaWhere = "";
        String url = "";
        boolean error = false;

        if (request.getParameter("all") != null) {
            clausulaWhere = "";
        } else if (cantidad != null) {
            clausulaWhere = " limit " + cantidad;
        } else {
            error = true;
        }

        if (!error) {
            //Llamar al método getAlumnosEquipo para obtener también los equipos
            IAlumnosDAO adao = new AlumnosDAO();
            ArrayList<Alumno> alumnos = adao.getAlumnosEquipo(clausulaWhere);
            request.setAttribute("alumnos", alumnos);
            url = "salida.jsp";
        } else {
            request.setAttribute("error", "No se han pasado parámetros");
            url = "error.jsp";
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
