<%-- 
    Document   : report.jsp
    Created on : Dec 12, 2020, 8:53:17 PM
    Author     : 789438
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
    <%
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=users.xls");
      
           
        
        
        %>
        <table>
             <th>Name</th>
           <th>Number of items</th>
       <c:forEach items="${users}" var="user">
          
                <tr>
                    
                   
                     <td>${user.getLastName()} , ${user.getFirstName()}</td>
                    
                       <td>${user.getItemsList().size()}</td>
                     
                </tr>
      </c:forEach>
               </table>

    </body>
    </html>
