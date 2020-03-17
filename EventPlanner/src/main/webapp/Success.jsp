<%-- 
    Document   : DeleteEvent
    Created on : 15.03.2020, 15:28:38
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
        <title>Action message</title>
    </head>
    <body>
        <header>
            <h1>Event Planner</h1>
            <p>by Andreas Bitzan and Moritz Withöft</p>
        </header>
        <main class="mainContainer">
            <%-- Use message bean to see ig there was a message--%>
            <jsp:useBean id="message" class="java.lang.String" scope="request"/>
            <c:choose>
                <c:when test="${not empty message}">
                    <p>${message}</p>
                </c:when>
                <c:otherwise>
                    <p>That was a success!</p>
                </c:otherwise>
            </c:choose>
            <a href="/EventPlanner/">Go back</a>
        </main>
    </body>
</html>
