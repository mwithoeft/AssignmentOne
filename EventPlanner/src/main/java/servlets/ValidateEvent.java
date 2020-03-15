package servlets;

import beans.EventBean;
import beans.HostBean;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tables.CustomEvent;
import tables.CustomEventDB;
import tables.CustomHost;
import tables.CustomHostDB;

/**
 *
 * @author hallo
 */
@WebServlet(name = "ValidateEvent", urlPatterns = {"/ValidateEvent"})
public class ValidateEvent extends HttpServlet {

    @Inject 
    private CustomEventDB eventDB;
    
    
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
        
        HttpSession session = request.getSession();
        HostBean hostBean = (HostBean) session.getAttribute("hosts");
        
        CustomEvent event = new CustomEvent();
        boolean allFilled = checkParameters(event, request, hostBean);
        
        
        if (allFilled) {
            eventDB.create(event);
            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/Success.jsp");
            dispatcher.forward(request, response);
        } else {
            EventBean eBean = new EventBean();
            eBean.addEvent(event);
            request.setAttribute("event", eBean);
            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/CreateEvent");
            dispatcher.forward(request, response);
        }


    }
    
    private boolean checkParameters(CustomEvent event, HttpServletRequest request, HostBean hostBean) {

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");

        String eventname = request.getParameter("eventname");
        String shortDescription = request.getParameter("shortdesc");
        String longDescription = request.getParameter("longdesc");
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");
        String startTime = request.getParameter("starttime");
        String endTime = request.getParameter("starttime");
        String hostId = request.getParameter("eventhost");

        /* Checking if all values are set and add them to event if so*/
        boolean allFilled = true;
        if (isNotFilled(eventname)) {
            allFilled = false;
        } else {
            event.setEventname(eventname);
        }

        if (isNotFilled(shortDescription)) {
            allFilled = false;
        } else {
            event.setShortDescription(shortDescription);
        }

        if (isNotFilled(longDescription)) {
            allFilled = false;
        } else {
            event.setLongDescription(longDescription);
        }

        Date parsedStartDate = today;
        if (isNotFilled(startDate)) {
            allFilled = false;
        } else {
            try {
                parsedStartDate = dateParser.parse(startDate);
                /* Checking if start date is today or after */
                if (parsedStartDate.compareTo(today) >= 0) {
                    event.setStartDate(parsedStartDate);
                } else {
                    allFilled = false;
                }
            } catch (ParseException ex) {
                allFilled = false;
            }
        }
        
        if (isNotFilled(endDate)) {
            allFilled = false;
        }  else {
            try {
                Date parsedEndDate = dateParser.parse(endDate);                
                /* Checking if end date is on or after start date */
                if (parsedEndDate.compareTo(parsedStartDate) >= 0) {
                    event.setEndDate(parsedEndDate);
                } else {
                    allFilled = false;
                }
            } catch (ParseException ex) {
                allFilled = false;
            }
        }
        
        if (isNotFilled(startTime)) {
            allFilled = false;
        } else {
            try {
                Date parsedStartTime = new SimpleDateFormat("hh:mm:ss").parse(startTime+":00");
                event.setStartTime(parsedStartTime);
            } catch (ParseException ex) {
                allFilled = false;
            }
        }
        
        if (isNotFilled(endTime)) {
            allFilled = false;
        } else {
            try {
                Date parsedEndTime = new SimpleDateFormat("hh:mm:ss").parse(endTime+":00");
                event.setEndTime(parsedEndTime);
            } catch (ParseException ex) {
                allFilled = false;
            }
        }
        
        if (isNotFilled(hostId) || hostId.equals("nohost")) {
            allFilled = false;
        } else {
            CustomHost host = hostBean.getHost(Integer.parseInt(hostId));
            if (host != null) {
                event.setEventHost(host);
            } else {
                allFilled = false;
            }
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
