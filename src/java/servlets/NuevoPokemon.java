/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StukemonEJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.StukemonEJB;
import entities.Trainer;
import entities.Pokemon;
import java.util.List;

/**
 *
 * @author Kelvin
 */
@WebServlet(name = "NuevoPokemon", urlPatterns = {"/NuevoPokemon"})
public class NuevoPokemon extends HttpServlet {
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NuevoPokemon</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NuevoPokemon at " + request.getContextPath() + "</h1>");
            out.println("<form action=\"EnterPokemon\" method=\"GET\">");
            out.println("<label>Nombre</label>");
            out.println("<input type=\"text\" name=\"nombre\">");
            out.println("<br>");
            out.println("<label>Tipo</label>");
            out.println("<input type=\"text\" name=\"tipo\">");
            out.println("<br>");
            out.println("<label>Habilidad</label>");
            out.println("<input type=\"text\" name=\"habilidad\">");
            out.println("<br>");
            out.println("<label>ATK</label>");
            out.println("<input type=\"text\" name=\"atk\">");
            out.println("<br>");
            out.println("<label>DEF</label>");
            out.println("<input type=\"text\" name=\"def\">");
            out.println("<br>");
            out.println("<label>SPEED</label>");
            out.println("<input type=\"text\" name=\"speed\">");
            out.println("<br>");
            out.println("<label>HP</label>");
            out.println("<input type=\"text\" name=\"hp\">");
            out.println("<br>");
            out.println("<label>Entrenador</label>");
            out.println("<select name=\"entrenador\">");
            try{
                List<Trainer> todosEntrenadores = ejb.selectAllTrainersWithPokemons();
                for(Trainer actualTrainer : todosEntrenadores){
                    out.println("<option value="+actualTrainer.getName()+">"+actualTrainer.getName()+"</option>");
                }
                
            }catch(Exception e){
            out.println("Error");
            }
            out.println("</select>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Register\">");
            out.println("</form>");
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
