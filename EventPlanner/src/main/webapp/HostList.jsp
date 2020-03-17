<%-- 
    Document   : HostList
    Created on : 17/03/2020, 1:57:39 PM
    Author     : hallo
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
        
        <main class="mainContainer">
            <c:if test="${not empty requestScope.message}" >
            <p class="notification">
                ${requestScope.message}
            </p>
        </c:if>
            <section class="hostList">
                <jsp:useBean id="hosts" class="beans.HostBean" scope="session"/>
                <c:choose>
                <c:when test="${hosts.isEmpty()==false}">
                    
                    <ul>
                        <c:forEach items="${hosts.getHostList()}" var="tmpHost">
                            <li>
                                
                               
                               <h3>${tmpHost}</h3>
                                 <a href="<c:url value = "/DeleteHost?id=${tmpHost.id}"/>">Delete</a>
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
       
            <a href="/EventPlanner/">Go back</a>
        </main>
        
    </body>
</html>
