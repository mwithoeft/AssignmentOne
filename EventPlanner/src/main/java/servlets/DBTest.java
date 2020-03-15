/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tables.CustomEvent;
import tables.CustomEventDB;
import tables.CustomHost;
import tables.CustomHostDB;

/**
 *
 * @author hallo
 */
@WebServlet(name = "DBTest", urlPatterns = {"/dbtest"})
public class DBTest extends HttpServlet {
    
    @Inject
    private CustomEventDB eventDB;
    @Inject
    private CustomHostDB hostDB;
    

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
            out.println("<title>Servlet DBTest</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DBTest at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void fillExamples() {
        CustomEvent event = new CustomEvent();
        CustomHost host = new CustomHost();
        
        host.setEventsHosted(0);
        host.setFirstname("Brad");
        host.setLastname("Pit");
        host.setLocation("University of Auckland");
        
        
        event.setEventname("Sunlight Festival");
        event.setStartDate(new Date(2020, 04, 15));
        event.setEndDate(new Date(2020, 04, 30));
        event.setShortDescription("A festival at sunlight");
        event.setLongDescription("A festival at sunlight where you can listen to your favourite music");
        event.setStartTime(new Date(2020, 03, 20, 0 ,0));
        event.setEndTime(new Date(2020, 03, 20, 0, 0));
        event.setEventHost(host);
        
        eventDB.create(event);
        
        
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
