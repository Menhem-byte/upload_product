<%-- 
    Document   : admin
    Created on : Dec 9, 2020, 2:20:38 PM
    Author     : gehad menhem 789438
--%>
<%@page import="models.Users"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Admin Page</title>
    </head>
    <body>
        
         <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
	 <h1><fmt:message key="message"/> ${username }${ LastName}</h1>
	   <fmt:message key="language"/>
        <form action="#" method="POST">
           
            <select name="ddlLanguage">
               
                <option value="en_US">English</option>
                 <option value="fr_FR">France</option>
                <option value="ar_AE">Arabic</option>
            </select>
            <input type="submit" value="<fmt:message key="button"/>"/>
        </form>
        
         <p>
            <c:if test="${message eq 'create'}">Inventory created</c:if>
            <c:if test="${message eq 'update'}">Inventory updated</c:if>
            <c:if test="${message eq 'delete'}">Inventory deleted</c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
            </p>
            
	</div>
      </div>
         </div>
      
    
        <div id="menubar">
             <ul id="menu">
             
          <li><a href="admin"  class="selected" >  <fmt:message key="admin"/> </a></li>
                <li><a  href="inventory"> <fmt:message key="inventory"/></a></li>
                <li><a href="manage"> <fmt:message key="manage"/></a></li> 
                <li><a href="login"> <fmt:message key="logout"/></a></li> 
                <li > <a href="report"> Generate report</a></li>
            </ul>
            </div>
	<div id="site_content">
           <div class="sidebar">
          <div class="sidebar_top"></div>
          <div class="sidebar_item">
            <h3>Search</h3>
  <form action="admin" method="POST">
                <input type="text" name="searchText">
                <input type="submit" value="Search">
                <input type="hidden" name="action" value="<fmt:message key="search"/>">
                <c:if test="${items != null}">
                    <table border="1">
                       <th>Item Name</th>
                       <th>Item Owner</th>
                       <c:forEach items="${items}" var="items">
                           <tr>
                               <td>${items.getItemName()}</td>
                               <td>${items.getOwner().getUsername()}</td>                 
                           </tr>
                       </c:forEach>
                    </table>
                </c:if>
             </form>
          </div>
          <div class="sidebar_base"></div>
        </div>
			 
			 
	 <div id="content">
        
            
            <table border="1">
                <th><fmt:message key="username"/></th>
                <th><fmt:message key="firstname"/></th>
                <th><fmt:message key="lastname"/></th>
                <th> <fmt:message key="delete"/></th>
                 <th> <fmt:message key="edit"/></th>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user. getUsername()}</td>
                     <td>${user.getFirstName()}</td>
                      <td>${user.getLastName()}</td>
                     
                      <td>
                           <form action="admin" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="user" value="${user.getUsername()}">
                <input type="submit" class="inputbutton" value="<fmt:message key="delete"/>">
            </form>
                      </td>
                        <td>
                           <form action="admin" method="get">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="user" value="${user.getUsername()}">
                <input type="submit" class="inputbutton" value="<fmt:message key="edit"/>">
            </form>
                      </td>
                        
                    
               
                
                </tr>
            </c:forEach>
            </table>  
        <c:if test="${selectedUser eq null}">
                <h2><fmt:message key="create"/></h2>
            <form action="admin" method="post">
                <table>
                   
                    <tr><td>
                          
                <label><fmt:message key="username"/></label>  
                        </td>
                        <td>
                <input type="text" class="textinput" name="username" value=""><br>
                      </td> 
                      </tr>
                      <tr> <td>
                <label><fmt:message key="password"/></label>
                </td>
                <td>
                <input type="password" class="textinput" name="password" value=""><br>
                      </td>
                      </tr>
                      <tr>
                      <td>
                <label><fmt:message key="email"/></label> 
                </td>
                <td>
                <input type="text" class="textinput"  name="email" value=""><br>
                </td>
                </tr>
                <tr>
                <td>
                <label><fmt:message key="firstname"/></label>  
                </td>
                <td>
                <input type="text" class="textinput" name="firstname" value=""><br>
                </td>
                </tr>
                <tr>
                <td>
                <label><fmt:message key="lastname"/></label> 
                </td>
                <td>
                <input type="text" class="textinput" name="lastname" value=""><br>
                </td>  
                </tr>
                <tr>
                    <td colspan="2">
                <c:choose>
                    <c:when test="${selectedUser.active}">
                    <label><fmt:message key="active"/></label><input type="checkbox" name="active" value="true" checked="true"><br>
                    </c:when>
                    <c:otherwise>
                 <label><fmt:message key="active"/></label><input type="checkbox" name="active" value="true" checked="false"><br>
                    </c:otherwise>
                </c:choose>
                </td>
                </tr>
                     <tr>
                    <td colspan="2">
                <c:choose>
                    <c:when test="${selectedUser.isAdmin}">
                    <label><fmt:message key="administrator"/></label><input type="checkbox" name="promote" value="true" checked="true" ><br>
                    </c:when>
                    <c:otherwise>
                        <label><fmt:message key="administrator"/></label><input type="checkbox" name="promote" value="true" checked="false" ><br>
                    </c:otherwise>
                </c:choose>
                </td>
                </tr>
                <tr>
                    <td colspan="2">
                <input type="hidden" name="action" value="create">
                <input type="submit" class="inputbutton" value="<fmt:message key="save"/>">
               </td>
                    </tr>
                </table>
            </form>
                </c:if>
                 <c:if test="${selectedUser ne null}">
                <h2><fmt:message key="edit"/></h2>
            <form action="admin" method="post">
               <table>
                   
                    <tr><td>
               
                <label><fmt:message key="username"/></label> 
                 </td>
                        <td>
                <input type="text" class="textinput" name="username" value="${selectedUser.username}" readonly><br>
                  </td> 
                      </tr>
                      <tr> <td>
               <label><fmt:message key="password"/></label> 
               </td>
                <td>
               <input type="password" class="textinput" name="password" value="${selectedUser.password}"><br>
                </td>
                      </tr>
                      <tr>
                      <td>
                          <label><fmt:message key="email"/></label> 
                 </td>
                <td>
                <input type="text" class="textinput" name="email" value="${selectedUser.email}"><br>
                     </td>
                </tr>
                <tr>
                <td>
                
                <label><fmt:message key="firstname"/></label> 
                </td>
                <td>
                <input type="text" class="textinput"name="firstname" value="${selectedUser.firstName}"><br>
                </td>
                </tr>
                <tr>
                <td>
                <label><fmt:message key="lastname"/></label>
                </td>
                <td>
                <input type="text" class="textinput" name="lastname" value="${selectedUser.lastName}"><br>
                </td>  
                </tr>
                <tr>
                 <td colspan="2">
                    <c:choose>
                    <c:when test="${selectedUser.active}">
                    <label><fmt:message key="active"/></label><input type="checkbox" name="active" value="true" checked="true"><br>
                    </c:when>
                    <c:otherwise>
                 <label><fmt:message key="active"/></label><input type="checkbox" name="active" value="true" checked="false"><br>
                    </c:otherwise>
                </c:choose>
                    </td>
                </tr>
                   <tr>
                    <td colspan="2">
                <c:choose>
                    <c:when test="${selectedUser.isAdmin}">
                   <label> <fmt:message key="administrator"/></label><input type="checkbox" name="promote" value="true" checked="true" ><br>
                    </c:when>
                    <c:otherwise>
                        
                         <label><fmt:message key="administrator"/></label><input type="checkbox" name="promote" value="true"  checked="false"><br>
                    </c:otherwise>
                </c:choose>
                </td>
                </tr>
                <tr>
                    <td colspan="2">
                <input type="hidden" name="action" value="edit">
                <input type="submit" class="inputbutton" value="<fmt:message key="save"/>">
               </td>
                    </tr>
                </table>
            </form>
                </c:if>
                
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
