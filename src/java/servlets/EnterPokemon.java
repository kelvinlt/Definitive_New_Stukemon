/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import beans.StukemonEJB;
import entities.*;

/**
 *
 * @author Kelvin
 */
@WebServlet(name = "EnterPokemon", urlPatterns = {"/EnterPokemon"})
public class EnterPokemon extends HttpServlet {

    @EJB
    StukemonEJB ejb;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String nombre = request.getParameter("nombre");
            String tipo = request.getParameter("tipo");
            String habilidad = request.getParameter("habilidad");
            Integer atk = Integer.parseInt(request.getParameter("atk"));
            Integer def = Integer.parseInt(request.getParameter("def"));
            Integer speed = Integer.parseInt(request.getParameter("speed"));
            Integer hp = Integer.parseInt(request.getParameter("hp"));
            String entrenador = request.getParameter("entrenador");

            Pokemon p = new Pokemon(nombre, tipo, habilidad, atk, def, speed, hp, 0);
            Trainer t = ejb.findTrainer(entrenador);
            p.setTrainer(t);
            ejb.insertPoke(p);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EnterPokemon</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EnterPokemon at " + request.getContextPath() + "</h1>");

            out.println("<h1>Nuevo pokemon creado!</h1>");
            out.println("<h1>Pokemon: " + nombre + "</h1>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
