<%-- 
    Document   : ShowEvent
    Created on : 11.03.2020, 11:41:02
    Author     : Andreas Bitzan, Moritz Withoeft
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
        <title>The Event</title>
    </head>
    <body>
        <%-- Get the event that should be displayed out of the request bean --%>
        <jsp:useBean id="currentevent" class="tables.CustomEvent" scope="request"/>

        <c:choose>
            <%-- Check if the event has content or if the bean was initialized with an empty body --%>
            <c:when test="${not empty currentevent}">
                <h1>${currentevent.getEventname()}</h1>
            </c:when>
        </c:choose>

        <main class="mainContainer">
            <c:choose>
                <%-- Check if bean actually contains content --%>
                <c:when test="${not empty currentevent}">

                    <h3>What?</h3>
                    <p>${currentevent.getLongDescription()}</p>

                    <h3>When?</h3>
                    <p>${currentevent.displayStartTime()}, from ${currentevent.startTimeString} to ${currentevent.endTimeString}</p>

                    <h3>Where?</h3>
                    <p>${currentevent.getEventHost().getLocation()}</p>

                    <h3>Who is the host?</h3>
                    <p>${currentevent.getEventHost().getFirstname()} ${currentevent.getEventHost().getLastname()} (Has hosted ${currentevent.getEventHost().eventsHosted} events so far)</p>
                </c:when>
                <c:otherwise>
                    <p>The correct bean was not found...</p>
                </c:otherwise>
            </c:choose>

            <a href="/EventPlanner/">Go back</a>
        </main>

    </body>
</html>
