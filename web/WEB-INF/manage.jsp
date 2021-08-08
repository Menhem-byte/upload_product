<%-- 
    Document   : manage
    Created on : Dec 9, 2020, 9:36:06 PM
    Author     : 789438
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="current" value="${param.ddlLanguage}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="i18n.myResource" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
         
         <link href="style_1.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title"/></title>
    </head>
    <body>
         <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
	<h1>Home Inventory  Web site</h1>
	 <h2><fmt:message key="message"/> ${username }${ LastName}</h2>
	</div>
      </div>
         </div>
     <div id="menubar">
        <ul id="menu">
              <li><a href="admin">  <fmt:message key="admin"/> </a></li>
                <li><a  href="inventory"> <fmt:message key="inventory"/></a></li>
                <li><a class="active" href="manage"> <fmt:message key="manage"/></a></li> 
                <li><a href="login"> <fmt:message key="logout"/></a></li> 
                
            </ul>
        </div>
                  <p>
          
            <c:if test="${message eq 'update'}">User  updated</c:if>
            <c:if test="${message eq 'delete'}">User is set to Inactive </c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
            </p>
            
       
	<div id="site_content">
	 <div id="content">
      
                
             
     
          
            <form action="manage" method="post">
                <table>
                    <th><h1><fmt:message key="edit"/></h1></th>
                <tr>
                    <td><label><fmt:message key="username"/></label> </td>
                    <td> <input type="text" name="username" value="${selectedUser.username}" readonly><br></td>
            </tr>
            <tr><td>
                    <label><fmt:message key="password"/></label></td><td> <input type="password" name="password" value="${selectedUser.password}"><br>
              </td></tr>
            <tr><td>
              <label><fmt:message key="email"/></label> </td><td>  <input type="text" name="email" value="${selectedUser.email}"><br>
              </td></tr>
            <tr><td>
              <label><fmt:message key="firstname"/> </label> </td><td>  <input type="text" name="firstname" value="${selectedUser.firstName}"><br>
              </td></tr>
            <tr><td>
             <label><fmt:message key="lastname"/></label> </td><td>   <input type="text" name="lastname" value="${selectedUser.lastName}"><br>
                  </td></tr>
            <tr><td colspan="2">
             <c:choose>
                    <c:when test="${selectedUser.active}">
                    <label><fmt:message key="active"/></label><input type="checkbox" name="active" value="true" checked="true"><br>
                    </c:when>
                    <c:otherwise>
                 <label><fmt:message key="active"/></label><input type="checkbox" name="active" value="false" checked="false"><br>
                    </c:otherwise>
                </c:choose>
                </td></tr>
            
                <input type="hidden" name="action" value="edit">
               
                <tr><td colspan="2">
                <input type="submit" class="inputbutton" value="<fmt:message key="save"/>">
               </td></tr>
                </table>
              </form>
            
               </div>
            </div>
   </div>
              <div id="footer">
      <p><a href="admin"> <fmt:message key="admin"/> </a> | <a href="inventory"><fmt:message key="inventory"/></a> | <a href="manage"><fmt:message key="manage"/></a> | <a href="login"><fmt:message key="logout"/></a> | </p>
      <p> <a href="https://www.html5webtemplates.co.uk/templates/"></a>I Downloaded the css from this Site </p>
      <p>Copyright &copy; Gehad Menhem | <a href="http://validator.w3.org/check?uri=referer">HTML5</a> | <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a> | <a href="http://www.html5webtemplates.co.uk">Simple web for Inventory Management System</a></p>
    </div>

  </body>  
</html>
