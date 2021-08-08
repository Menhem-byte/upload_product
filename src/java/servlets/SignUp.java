/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Users;
import services.UsersServices;

/**
 *
 * @author 789438
 */
public class SignUp extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
          UsersServices us = new UsersServices();
          
         String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName=request.getParameter("firstname");
        String lastName=request.getParameter("lastname");
        boolean active=true;
        boolean isAdmin=false;
         
       boolean exist=false;
        try {
            List<Users> users = us.getAll();
           for(int i=0;i<users.size();i++){
               if(username.equals(users.get(i).getUsername())){
                    session.setAttribute("message", "error");
                    exist=true;
               }
           }
        } catch (Exception ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(exist==false){
             
                   try {
                  us.insert(username,password,email,firstName,lastName,active,isAdmin);
                  
                  System.out.println();
                  session.setAttribute("message", "create");
                  
              } catch (Exception ex) {
                  Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
              }
         }
            
         
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
    }

 
}
