<%-- 
    Document   : CreateEvent
    Created on : 10.03.2020, 19:25:39
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
        <title>Create new Event</title>
    </head>
    <body>
        <%-- Check if a new event should be created, or if an event should be edited, depends if request bean contains an event --%>
        <jsp:useBean id="currentevent" class="tables.CustomEvent" scope="request"/>
        <%-- Get all hosts out of the session, bean was set in IndexServlet --%>
        <jsp:useBean id="hosts" class="beans.HostBean" scope="session" />
         <header>
        <h1>Create New Event</h1>
        </header>
        <%-- Check if the eventbean has been instantiated by the jsp or the actual request, since useBean creates a bean if not given--%>
         <c:if test="${currentevent.selfInitialized==true}" >
            <p class="notification">
                Please fill out all fields!
            </p>
        </c:if>
          <main class="mainContainer">

        <form class="createEventForm" action="ValidateEvent" method="POST">
        <c:choose>
            <%-- If event was featured in request, use it to autofill the form --%>
            <c:when test="${not empty currentevent}">
                
                  <div>
                      <label for="eventname">Name of event</label>
                      <input type="text" name="eventname" value="${currentevent.eventname}"/>
                  </div>
                   <div>
                      <label for="shortdesc">Short description</label>
                      <textarea rows="4" cols="50" name="shortdesc" >${currentevent.shortDescription}</textarea>
                  </div>
                  <div>
                     <label for="longdesc">Long description</label>
                     <textarea id="longdesc" rows="4" cols="50" name="longdesc">${currentevent.longDescription}</textarea>
                  </div>
                  <div>
                      <label for="startdate">Start Date ${currentevent.startDateString}</label>
                      <input type="date" name="startdate" value="${currentevent.startDateString}" />
                  </div>
                  <div>
                      <label for="starttime">Start Time</label>
                      <input type="time" name="starttime" value="${currentevent.startTimeString}" />
                  </div>
                  <div>
                      <label for="enddate">End Date ${currentevent.endDateString}</label>
                      <input type="date" name="enddate" value="${currentevent.endDateString}"/>
                  </div>
                  <div>
                      <label for="endtime">End Time</label>
                      <input type="time" name="endtime" value="${currentevent.endTimeString}"/>
                  </div>
                  <div>
                      <label for="eventhost">Host</label>
                      <%-- Display every host in a dropdown --%>
                      <select id="eventhost" name="eventhost" >
                      
                          ${hosts.hostList}
                         <c:choose>
                         <c:when test="${hosts.isEmpty()==false}">
                             <c:forEach items="${hosts.getHostList()}" var="host">
                                  <option value="${host.getId()}">${host}</option>
                                 </c:forEach>
                         </c:when>
                         <c:otherwise>
                             <option value="nohost">No host defined yet</option>
                         </c:otherwise>
                         </c:choose>
                      </select>
                         <a href="<%= response.encodeURL("CreateHost") %>">Add new Host</a>
                  </div>
                  <div>
                  <input type="submit" value="Create" class="greenBtn submitBtn"/>
                  <a class="redBtn greenBtn" href="/EventPlanner/">Cancel</a>

                  </div>
                 
                
         
                
            </c:when>
            <c:otherwise>
           <%-- No bean with an editable event was found, display empty fields --%>
                <div>
                      <label for="eventname">Name of event</label>
                      <input type="text" name="eventname" />
                  </div>
                  <div>
                      <label for="shortdesc">Short description</label>
                      <textarea  rows="4" cols="50"  name="shortdesc" >
                          
                      </textarea>
                  </div>
                  <div>
                     <label for="longdesc">Long description</label>
                     <textarea id="longdesc" rows="4" cols="50" name="longdesc">
                    
                     </textarea>
                  </div>
                  <div>
                      <label for="startdate">Start Date</label>
                      <input type="date" name="startdate"  />
                  </div>
                  <div>
                      <label for="starttime">Start Time</label>
                      <input type="time" name="starttime" />
                  </div>
                  <div>
                      <label for="enddate">End Date</label>
                      <input type="date" name="enddate" />
                  </div>
                  <div>
                      <label for="endtime">End Time</label>
                      <input type="time" name="endtime" />
                  </div>
                  <div>
                      <label for="eventhost">Host</label>
                      <select id="eventhost" name="eventhost" >
                         <c:choose>
                         <c:when test="${hosts.isEmpty()==false}">
                             <c:forEach items="${hosts.hostList}" var="host">
                                  <option value="${host.getId()}">${host}</option>
                                 </c:forEach>
                         </c:when>
                         <c:otherwise>
                             <option value="nohost">No host defined yet</option>
                         </c:otherwise>
                         </c:choose>
                      </select>
                         <a href="<%= response.encodeURL("CreateHost") %>">Add new Host</a>
                  </div>
                  <div>
                  <input type="submit" value="Create" class="greenBtn submitBtn"/>
                  <a class="redBtn greenBtn" href="/EventPlanner/">Cancel</a>
                  </div>
            </c:otherwise>
        </c:choose>
        </form>
 
        </main>
    </body>
</html>
