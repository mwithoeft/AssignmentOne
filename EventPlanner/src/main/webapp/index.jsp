<%-- 
    Document   : index
    Created on : 10.03.2020, 18:43:29
    Author     : Andreas Bitzan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event planner</title>
    </head>
    <body>
        <header>
        <h1>Event Planner</h1>
        <p>by Andreas Bitzan and Moritz Withöft</p>
        </header>
        <main>
            <%-- <h2>Events happening this week</h2>
            <section>
                <jsp:useBean id="events" class="beans.EventBean"/>
                <c:choose>
                <c:when test="${not empty events}">
                    
                    <ul>
                        <c:forEach items="${events}" var="tmpEvent">
                            <li>
                                <a href="<%= response.encodeURL("ShowEvent.jsp") %>">
                                <c:out value="${tmpEvent}"/>
                                <a href="/EditServlet?id={tmpEvent.id}">Edit Event</a>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <div><h3>No events registered so far.</h3></div>
                </c:otherwise>
                </c:choose>

            </section>
                <section>
                    <a href='<%= response.encodeURL("CreateEvent.jsp") %>'>Create new event</a>
                </section> --%>
            Test
        </main>
        
    </body>
</html>

