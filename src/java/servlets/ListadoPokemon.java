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
import beans.StukemonEJB;
import entities.Trainer;
import entities.Pokemon;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Kelvin
 */
@WebServlet(name = "ListadoPokemon", urlPatterns = {"/ListadoPokemon"})
public class ListadoPokemon extends HttpServlet {

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
            
            List<Pokemon> allPokemon = ejb.listaPokemonRanking();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListadoPokemon</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListadoPokemon at " + request.getContextPath() + "</h1>");
            for (Pokemon pokeActual : allPokemon) {
            out.println("<form action=\"BorrarPoke\" method=\"GET\">");
            out.println("<div>Nombre: " + pokeActual.getName() + "|| Nivel: "+pokeActual.getLevel()+"|| HP: " + pokeActual.getLife()+ "</div>");
            out.println("<div>Entrenador: " + pokeActual.getTrainer().getName() + "</div>");
            out.println("<input type=\"hidden\" name=\"name\" value="+pokeActual.getName()+">");
            out.println("<input type=\"submit\" value=\"BORRAR\">");
            out.println("</form>");
            out.println("<br>");
            
            }
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
