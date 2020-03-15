<%-- 
    Document   : CreateEvent
    Created on : 10.03.2020, 19:25:39
    Author     : Andreas Bitzan
--%>

<%@page import="beans.HostBean"%>
<%@page import="java.util.List"%>
<%@page import="tables.CustomHost"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style><%@include file="/style.css"%></style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new Event</title>
    </head>
    <body>
         <header>
        <h1>Create New Event</h1>
        </header>
          <main>
         <form action="insertevent" method="POST">
         <p>
             <label for="eventname">Name of event</label>
            <input type="text" name="eventname"/>
         </p>
         <p>
             <label for="shortdesc">Short description</label>
             <input type="text" naeme="shortdesc" />
         </p>
         <p>
            <label for="longdesc">Long description</label>
            <textarea id="longdesc" rows="4" cols="50" name="longsdesc">
            </textarea>
         </p>
         <p>
             <label for="eventdate">Date</label>
             <input type="date" name="eventdate" />
         </p>
         <p>
             <label for="eventhost">Host</label>
             <select id="eventhost" name="eventhost" >
                 <jsp:useBean id="hostbean" class="beans.HostBean" scope="session"/>
                
                <c:choose>
                <c:when test="!${hostbean.isEmpty()}">
                     <c:forEach items="${hostbean}" var="host">
                         <option value="${hostbean}">${hostbean}</option>
                        </c:forEach>
                </c:when>
                <c:otherwise>
                    <option value="nohost">No host defined yet</option>
                </c:otherwise>
                </c:choose>
             </select>
                <a class="block" href="<%= response.encodeURL("CreateHost.jsp") %>">Add new Host</a>
         </p>
         <input type="submit" value="Create" class="greenBtn submitBtn"/>
         <a class="redBtn greenBtn" href="<%= response.encodeURL("index.jsp") %>">Cancel</a>
      </form>
        
          ${requestScope.LOL}
          ${requestScope.LOL.getHostList()}
           <c:forEach items="    ${requestScope.LOL.getHostList()}" var="host">
                         <h1>${host.toString()}</h1>
                        </c:forEach>
          Session:
          ${sessionScope.LOL}
      
        </main>
    </body>
</html>
