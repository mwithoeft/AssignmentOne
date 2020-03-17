package servlets;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tables.CustomHost;
import tables.CustomHostDB;

/**
 * Servlet to validate Host input
 *
 * @author Andreas Bitzan, Moritz Withoeft
 */
@WebServlet(name = "ValidateHost", urlPatterns = {"/ValidateHost"})
public class ValidateHost extends HttpServlet {

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

        CustomHost host = new CustomHost();
        host.setSelfInitialized(true);
        boolean allFilled = checkParameters(host, request);

        if (allFilled) {
            hostDB.create(host);
            request.setAttribute("message", "Host has been successfully created!");
            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/Success.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("host", host);
            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/CreateHost");
            dispatcher.forward(request, response);
        }

    }

    /**
     * Checks all the input fields for correctness.
     *
     * @param host Host instance to be filled with parameters
     * @param request HTTP-Request
     * @return Returns if all input fields are correctly filled
     */
    private boolean checkParameters(CustomHost host, HttpServletRequest request) {
        boolean allFilled = true;

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String location = request.getParameter("location");

        if (!isNotFilled(firstname)) {
            host.setFirstname(firstname);
        } else {
            host.setFirstname("");
        }

        if (!isNotFilled(lastname)) {
            host.setLastname(lastname);
        } else {
            host.setLastname("");
        }

        /*Only location is mandatory*/
        if (isNotFilled(location)) {
            allFilled = false;
            host.setLocation("");
        } else {
            host.setLocation(location);
        }

        return allFilled;
    }

    private boolean isNotFilled(String s) {
        return (s == null || s.equals(""));
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
