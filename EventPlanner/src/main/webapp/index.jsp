<%-- 
    Document   : index
    Created on : 10.03.2020, 18:43:29
    Author     : Andreas Bitzan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style><%@include file="/style.css"%></style>
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
            <h2 class="subheader">Upcoming events</h2>

            <section class="eventSection">
                <jsp:useBean id="events" class="beans.EventBean" scope="session"/>
                <c:choose>
                <c:when test="${events.isEmpty()==false}">
                    
                    <ul>
                        <c:forEach items="${events.getEventList()}" var="tmpEvent">
                            <li class="card">
                                
                               
                                    <h3>${tmpEvent.eventname}</h3>
                                    <p>${tmpEvent.getStartDate()}</p>
                                    <p>${tmpEvent.getShortDescription()}</p>
                                    
                                <div class="bottomBtns">
                                <a href="<c:url value = "/ShowEvent?id=${tmpEvent.id}"/>">Show more</a>
                                  <a href="<c:url value = "/CreateEvent?id=${tmpEvent.id}"/>">Edit</a>
                                 <a href="<c:url value = "/DeleteEvent?id=${tmpEvent.id}"/>">Delete</a>
                                </div>
                          
                            </li>
                        </c:forEach>
                    </ul>
          
   
                </c:when>
                <c:otherwise>
                    <div><h3>No events registered so far...</h3>
        
                    </div>
                </c:otherwise>
                </c:choose>

            </section>
                <section class="create">
                    <a class="greenBtn" href='CreateEvent'>+ Create new event</a>
                </section>
       

        </main>
        
    </body>
</html>

