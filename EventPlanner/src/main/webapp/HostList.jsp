<%-- 
    Document   : HostList
    Created on : 17/03/2020, 1:57:39 PM
    Author     : Andreas Bitzan, Moritz Withoeft
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
        <title>Host List</title>
    </head>
    <body>
        <header>
        <h1>Lists of all hosts</h1>
        <%-- Just check if a message was set, no creating of a bean necessary --%>
        <c:if test="${not empty requestScope.message}" >
            <p class="notification">
                ${requestScope.message}
            </p>
        </c:if>
        </header>
        <main class="mainContainer">

            <section class="hostList">
                <%-- Get all the hosts out of the session, was set in IndexServlet --%>
                <jsp:useBean id="hosts" class="beans.HostBean" scope="session"/>
                <c:choose>
                    <%-- Check if there are actually hosts inside the session bean --%>
                    <c:when test="${hosts.isEmpty()==false}">

                        <ul>
                            <%-- Display every host in a list --%>
                            <c:forEach items="${hosts.getHostList()}" var="tmpHost">
                                <li>                           
                                   <p>${tmpHost},  <strong>${tmpHost.getLocation()} <a href="<c:url value = "/DeleteHost?id=${tmpHost.id}"/>">Delete</a></strong></p> 
                                  
                                   <hr>
                                    
                                </li>
                            </c:forEach>
                        </ul>

                    </c:when>
                    <c:otherwise>
                    <div>
                        <h3 class="warning">No hosts registered so far...</h3>
                    </div>
                    </c:otherwise>
                </c:choose>

            </section>
            <a class="block" href="<%= response.encodeURL("CreateHost") %>">Add new Host</a>
            <a class="block" href="/EventPlanner/">Go back</a>
        </main>
        
    </body>
</html>
