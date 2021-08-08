<%-- 
    Document   : category
    Created on : Dec 10, 2020, 7:02:18 PM
    Author     : 789438
--%>
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
        <title>Categories  Page</title>
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
                <li><a class="active" href="inventory"> <fmt:message key="inventory"/></a></li>
                <li><a href="manage"> <fmt:message key="manage"/></a></li> 
                <li><a href="login"> <fmt:message key="logout"/></a></li> 
                
            </ul>
              </div>
	<div id="site_content">
	 <div id="content">  
        <p>
            <c:if test="${message eq 'create'}">Inventory created</c:if>
            <c:if test="${message eq 'update'}">Inventory updated</c:if>
            <c:if test="${message eq 'delete'}">Inventory deleted</c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
            </p>
            
            <table border="1">
                <th>Category Name</th>
                <th> Edit</th>
                   <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.getCategoryName()}</td>
                     <td>${category.getCategoryID()}</td>
                     
                <td>
                           <form action="category" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="categoryid" value="${category.getCategoryID()}">
                <input type="submit" class="inputbutton" value="Delete">
            </form>
                      </td>
                        <td>
                           <form action="category" method="get">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="categoryname" value="${category.getCategoryName()}" >
                <input type="hidden" name="categoryid" value="${category.getCategoryID()}">
                <input type="submit" class="inputbutton" value="Edit">
            </form>
                      </td>
                </tr>
            </c:forEach>
            </table>  
            
             <c:if test="${selectedCategory eq null}">
                <h2>Create a New Category</h2>
            <form action="category" method="post">
               
             
               Category Name: <input type="text" class="textinput" name="categoryname" value=""><br>
              
                <input type="hidden" name="action" value="create">
                <input type="submit" class="inputbutton" value="Save">
              
            </form>
                </c:if>
            
            
                <c:if test="${selectedCategory ne null}">
                <h2>Edit  Category</h2>
            <form action="category" method="post">
               
                <label> Category ID:</label>   <input type="text" class="textinput" name="categoryid" value="${selectedCategory.categoryID}"><br>
              <label>Category Name: </label>   <input type="text" class="textinput" name="categoryname" value="${selectedCategory.categoryName}"><br>
              
                <input type="hidden" name="action" value="edit">
                <input type="submit" class="inputbutton" value="Save">
              
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
