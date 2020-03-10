<%-- 
    Document   : CreateEvent
    Created on : 10.03.2020, 19:25:39
    Author     : Andreas Bitzan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            Last Name:
            <input type="text" name="lastname"/>
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
                <jsp:useBean id="hosts" class="beans.HostBean"/>
                <c:choose>
                <c:when test="${not empty hosts}">
                     <c:forEach items="${hosts}" var="host">
                         <option value="${host}">${host}</option>
                        </c:forEach>
                </when>
                <c:otherwise>
                    <option value="nohost">No host defined yet</option>
                </c:otherwise>
                </c:choose>
             </select>
         </p>
         <input type="submit"/>
      </form>
        </main>
    </body>
</html>
