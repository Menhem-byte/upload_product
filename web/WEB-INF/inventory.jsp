<%-- 
    Document   : inventory
    Created on : Dec 9, 2020, 4:03:04 PM
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
	
          <h1>Inventory Page</h1>
	 <h2><fmt:message key="message"/> ${username }${ LastName}</h2>
            <fmt:message key="language"/>
        <form action="#" method="POST">
           
            <select name="ddlLanguage">
               
                <option value="en_US">English</option>
                 <option value="fr_FR">France</option>
                <option value="ar_AE">Arabic</option>
            </select>
            <input type="submit" value="<fmt:message key="button"/>"/>
        </form>
        
        
	</div>
      </div>
         </div>
             
             <div id="menubar">
        <ul id="menu">
         <li><a href="admin">  <fmt:message key="admin"/> </a></li>
                <li><a class="selected" href="inventory"> <fmt:message key="inventory"/></a></li>
                <li><a href="manage"> <fmt:message key="manage"/></a></li> 
                <li><a href="login"> <fmt:message key="logout"/></a></li> 
                
            </ul>
                 </div>
                  
        <p>
            <c:if test="${message eq 'create'}">Inventory created</c:if>
            <c:if test="${message eq 'update'}">Inventory updated</c:if>
            <c:if test="${message eq 'delete'}">Inventory deleted</c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
            </p>
            
	<div id="site_content">
	 <div id="content">
                
      
        
      
            
            <table border="1">
                <th><fmt:message key="category"/></th>
                <th><fmt:message key="firstname"/></th>
                <th><fmt:message key="price"/></th>
                <th> <fmt:message key="pic"/></th>
                 <th><fmt:message key="delete"/></th>
                <th> <fmt:message key="edit"/></th>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td>${item.getCategory().getCategoryName()}</td>
                   
                     <td>${item.getItemName()}</td>
                      <td>${item.getPrice()}</td>
                      <td><image src="images/${item. getFilename()}" width="100" height="60"/></td>
                      <td>
         <form action="inventory" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="itemId" value="${item.itemID}">
                <input type="submit" class="inputbutton" value="Delete" >
          </form>
                    
                      </td>
                  <td>
            <form action="inventory" method="get" enctype="multipart/form-data">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="itemId" value="${item.itemID}">
                <input type="submit" class="inputbutton" value="Edit">
            </form>
                      </td>
                
                </tr>
            </c:forEach>
                </table>
             <c:if test="${selectedItem eq null}">
            <h2><fmt:message key="create"/></h2>
            <form action="inventory" method="post" enctype="multipart/form-data">
                
                      <label for="cate"><fmt:message key="category"/> :</label>
                      <select id="cate" name="category">
                      <c:forEach items="${categories}"  var="category">
                          
                      <option value="${category.getCategoryID()}">${category.getCategoryName()}
                      </option>
                      
                    </c:forEach>
                       </select><br>
               
                       <label><fmt:message key="name"/> :</label><input type="text" class="textinput" name="itemname" value=""><br>
                <label><fmt:message key="price"/> :</label>   <input type="text" class="textinput" name="price" value=""><br>
                <label><fmt:message key="link"/> :</label> <input type="file" name="file">
                <input type="hidden" name="action" value="create">
                <input type="submit" class="inputbutton" value="Create">
              
            </form>
             </c:if>
             <c:if test="${selectedItem ne null}">
                <h2><fmt:message key="edit"/></h2>
            <form action="inventory" method="post" enctype="multipart/form-data">
                 <label for="cate">Category</label>
                 <select id="cate" name="category" required>
                      <c:forEach items="${categories}"  var="category">
                          
                     <option value="${category.categoryID}"
                      ${selectedItem.category.categoryName == category.categoryName ? 'selected="selected"' : null }
                 >
                 ${category.categoryName}
                   </option>
                      
                    </c:forEach>
                       </select><br>
               
               <label> Name :</label>  <input type="text" class="textinput" name="itemname" value="${selectedItem.itemName}"><br>
               <label>Price :</label>   <input type="text" class="textinput" name="price" value="${selectedItem.price}"><br>
               <label>Image Link :</label> <input type="file" name="file" value="${pathfile}">
                  <input type="hidden" name="itemId" value="${selectedItem.itemID}">
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
