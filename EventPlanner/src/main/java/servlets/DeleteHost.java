/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
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
 * @author Andreas Bitzan, Moritz Withoeft
 */
@WebServlet(name = "DeleteHost", urlPatterns = {"/DeleteHost"})
public class DeleteHost extends HttpServlet {

    @Inject
    private CustomHostDB hostDB;
    @Inject
    private CustomEventDB eventDB;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * Makes sure host can only be deleted if it doesn'T have any events.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/Success.jsp");

        String id = request.getParameter("id");
        int hostID = Integer.parseInt(id);

        CustomHost host = hostDB.findById(hostID);
        List<CustomEvent> events = eventDB.findAll();

        if (host == null) {
            request.setAttribute("message", "Error! Host could not be deleted");
            dispatcher = getServletContext().
                    getRequestDispatcher("/HostList.jsp");
        } else {
            boolean canDelete = true;
            for (CustomEvent e : events) {
                if (e.getEventHost().getId() == hostID) {
                    canDelete = false;
                }
            }
            if (canDelete) {
                hostDB.delete(host);
                request.setAttribute("message", "Host has been successfully deleted!");
                dispatcher = getServletContext().
                        getRequestDispatcher("/Success.jsp");
            } else {
                request.setAttribute("message", "Host can't be deleted since it is still hosting an event!");
                dispatcher = getServletContext().
                        getRequestDispatcher("/HostList.jsp");
            }
        }

        dispatcher.forward(request, response);
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
