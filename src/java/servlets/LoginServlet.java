/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Users;
import services.AccountServices;

/**
 *
 * @author 789438
 */
public class LoginServlet extends HttpServlet {

  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
           HttpSession session=request.getSession();
        session.invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
      
        String action = request.getParameter("action");
            if(action !=null && action.equals("signup")){
            getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
          
    }
           
      
    }
       

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String username=request.getParameter("username");
        
         String password=request.getParameter("password");
         AccountServices as =new AccountServices();
         Users user =as.login(username, password);
         if(user==null){
             getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
             return;
         }
         HttpSession session=request.getSession();
         session.setAttribute("username", username);
       String  lastname= user.getLastName();
         session.setAttribute("LastName", lastname);
         if(user.getIsAdmin()==true  ){
             if(user.getActive()==true){
              response.sendRedirect("admin");
             }
             else{
             response.sendRedirect("login");
             }
        
         }
         else{
             if(user.getActive()==true){
              response.sendRedirect("inventory");
             }
             else{
                  response.sendRedirect("login");
             }
        
         }
        
      
    }

   
}
