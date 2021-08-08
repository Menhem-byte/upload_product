<%-- 
    Document   : signup
    Created on : Dec 9, 2020, 1:24:14 PM
    Author     : 789438
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style_1.css" />
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
         <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
	<h1>Home Inventory  Web site</h1>
          <h2>Registration Page Page</h2>
          </div>
      </div>
         </div>
	   <p>
            <c:if test="${message eq 'create'}"> Thank you for Register : User created</c:if>
            <c:if test="${message eq 'error'}">Sorry, this username is already exist.</c:if>
            </p>
	
        
      
            
       <h1>Create a register form</h1>
<p>Please fill the form below ....Thanks</p>
<form action="signup" method="post">
 <table>
     <tr>
         <td><label for="username"><b>Username : </b></label></td>
         <td><input type="text" placeholder="Enter username" name="username" required></td>
     </tr>
     <tr>
         <td><label for="password"><b>Password : </b></label></td>
         <td><input type="password" placeholder="Enter password" name="password" required></td>
     </tr>
     <tr>
         <td><label for="email"><b>E-mail : </b></label></td>
         <td><input type="email" placeholder="Enter email" name="email" required></td>
     </tr>
     <tr>
         <td><label for="firstname"><b>First name : </b></label></td>
         <td><input type="text" placeholder="Enter first name" name="firstname" required></td>
     </tr>
     <tr>
         <td><label for="lastname"><b>Last name : </b></label></td>
         <td><input type="text" placeholder="Enter last name" name="lastname" required></td>
     </tr>

     <tr>
        
         <td><input type="submit" value="signup"></td>
          <td><a href="login"><fmt:message key="logout"/>Return to Sign In</a></td>
     </tr>
      
        
         
    

</table>
</form>
 </div>
           
  
                 <div id="footer">
      <p><a href="index.html">Home</a> | <a href="examples.html">Examples</a> | <a href="page.html">A Page</a> | <a href="another_page.html">Another Page</a> | <a href="contact.html">Contact Us</a></p>
      <p>Copyright &copy; simplestyle_horizon | <a href="http://validator.w3.org/check?uri=referer">HTML5</a> | <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a> | <a href="http://www.html5webtemplates.co.uk">Simple web templates by HTML5</a></p>
    </div>
    </body>
</html>
