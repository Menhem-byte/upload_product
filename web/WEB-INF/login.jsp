<%-- 
    Document   : login
    Created on : Dec 9, 2020, 12:53:24 PM
    Author     : 789438
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      
         <link rel="stylesheet" href="style_1.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
     <body >
   <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
	<h1>Home Inventory  Web site</h1>
          <h2>Login Page</h2>
	
	</div>
      </div>
         </div>
    
   
	<div id="site_content">
	 <div id="content">
        
         
        <table>
            <tr>
                <td>
        <form action="login" method="post">
            <label>Username :</label> <input type="text"  class="textinput" name="username" value=${username}><br><br>
            <label>Password :</label> <input type="password" class="textinput" name="password"><br>
            <br>
            <br>
            <input type="submit" class="inputbutton"  value="Sign in">
        </form>
            </td>
            <td>
            <form action="signup" method="post">
               <h1>new User Sign Up Here</h1> 
               
                <input  type="submit" class="inputbutton"   value="Sign Up">
            </form>
                </td>
            </tr>
            </table>
       
  </div>
            </div>
   </div>
                <div id="footer">
      <p>Thanks you for using my website And this website is a Final Project </p>
      <p> <a href="https://www.html5webtemplates.co.uk/templates/"></a>I Downloaded the css from this Site </p>
      <p>Copyright &copy; Gehad Menhem | <a href="http://validator.w3.org/check?uri=referer">HTML5</a> | <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a> | <a href="http://www.html5webtemplates.co.uk">Simple web for Inventory Management System</a></p>
    </div>
    </body>
</html>
