<%-- 
    Document   : index
    Created on : 10.03.2020, 18:43:29
    Author     : Andreas Bitzan, Moritz Withoeft
--%>
<%-- Including the necessary headers--%>
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
            <p>Do NOT view in Internet Explorer!</p>
        </header>
        <main>
            <h2 class="subheader">Upcoming events</h2>

            <section class="eventSection">
                <%-- Load the bean containing all events out of the current session (set in IndexServlet)--%>
                <jsp:useBean id="events" class="beans.EventBean" scope="session"/>
                <c:choose>
                    <%-- Check if there are events saved, otherwise display a message --%>
                    <c:when test="${events.isEmpty()==false}">

                        <ul>
                            <%-- Display every event in a "card" like manner --%>
                            <c:forEach items="${events.getEventList()}" var="tmpEvent">
                                <li class="card">


                                    <h3>${tmpEvent.eventname}</h3>
                                    <p>${tmpEvent.displayStartTime()}</p>
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
                    <%-- The session bean was empty or not set --%>
                    <c:otherwise>
                        <div><h3 class="warning">No events registered so far...</h3>

                        </div>
                    </c:otherwise>
                </c:choose>

            </section>
            <section class="create">
                <a class="greenBtn" href='<c:url value = "/CreateEvent"/>'>+ Create new event</a>
                <a class="greenBtn" href='<c:url value = "/HostList.jsp"/>'>List of Hosts</a>
            </section>


        </main>

    </body>
</html>

