<%-- 
    Document   : CreateEvent
    Created on : 10.03.2020, 19:25:39
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
        <title>Create new Host</title>
    </head>
    <body>
         <header>
        <h1>Create New Host</h1>
        </header>
          <main>
         <form action="ValidateHost" method="POST">
         <p>
             <label for="firstname">First name of host</label>
            <input type="text" name="firstname"/>
         </p>
         <p>
             <label for="lastname">Last name of host</label>
             <input type="text" name="lastname" />
         </p>
         <p>
            <label for="location">Location of Host</label>
            <input type="text" name="location" />
         </p>
         <input type="submit" value="Create" class="greenBtn submitBtn"/>
         <a class="redBtn greenBtn" href="<%= response.encodeURL("CreateEvent") %>">Cancel</a>
      </form>
        </main>
    </body>
</html>
