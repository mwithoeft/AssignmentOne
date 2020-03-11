<%-- 
    Document   : ShowEvent
    Created on : 11.03.2020, 11:41:02
    Author     : Andreas Bitzan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="./style.css" rel="stylesheet" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Event</title>
    </head>
    <body>
           <main>
             <jsp:useBean id="singlevent" class="tables.CustomHost" scope="request"/>
              <c:choose>
                <c:when test="${not empty singleevent}">
                        <h1>${singleevent.name}</h1>
                </c:when>
                <c:otherwise>
                    <p>The correct bean was not found...</p>
                </c:otherwise>
              </c:choose>
 
        
        </main>
    </body>
</html>
