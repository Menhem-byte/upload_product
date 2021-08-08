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
import services.ItemsServices;
import services.UsersServices;

/**
 *
 * @author 789438
 */
public class ManageServlet extends HttpServlet {

   
 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
         UsersServices us = new UsersServices();
               String action = request.getParameter("action");
               
              
            String userSelected=(String) session.getAttribute("username");
            System.out.println("this is the find user operation "+userSelected);
            Users selectedUser;
            try{
                selectedUser=us.get(userSelected);
                request.setAttribute("selectedUser", selectedUser);
            }
            catch (Exception ex) {
            Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, ex);
           
        }
            
                try {
             session = request.getSession();
            //String username = (String) session.getAttribute("username");
            List<Users> users = us.getAll();
            System.out.println(users);
                    
            request.setAttribute("users", users);
           
          
        } catch (Exception ex) {
            Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

     
    
                  getServletContext().getRequestDispatcher("/WEB-INF/manage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           HttpSession session = request.getSession();
      

       UsersServices us = new UsersServices();
       ItemsServices is = new ItemsServices();
      System.out.println("hello one ");
        
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName=request.getParameter("firstname");
        String lastName=request.getParameter("lastname");
        String activeString=request.getParameter("active");
        boolean active=Boolean.parseBoolean(activeString);
        boolean isAdmin=false;
          String change =request.getParameter("selected");
       String message = request.getParameter("message");
        System.out.println("hello two ");
        try {
            switch (action) {
               
                case "edit":
                   
                  us.update( username,  password,  email,  firstName,  lastName,  active,  isAdmin);
                  // request.setAttribute("message", "update");
                    break;
                case "delete":
                    
                    active=false;
                    String user = request.getParameter("user");
                     System.out.println("the username is "+user);
                  
                     us.update( username,  password,  email,  firstName,  lastName,  active,  isAdmin);
            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(InventoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

       
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/manage.jsp").forward(request, response);
    }

  
}
