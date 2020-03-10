<%-- 
    Document   : ShowEvent
    Created on : 11.03.2020, 11:41:02
    Author     : Andreas Bitzan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Event</title>
    </head>
    <body>
           <main>
             <jsp:useBean id="singlevent" class="CustomEvent" scope="request"/>
              <c:choose>
                <c:when test="${not empty singleevent}">
                        <h1>${singleevent.name}</h1>
                </when>
                <c:otherwise>
                    <p>The correct bean was not found...</p>
                </c:otherwise>
              </c:choose>
 
        
        </main>
    </body>
</html>
