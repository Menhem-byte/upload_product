/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Items;
import models.Users;
import services.ItemsServices;
import services.UsersServices;

/**
 *
 * @author 789438
 */
public class AdminServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        UsersServices us = new UsersServices();
               String action = request.getParameter("action");
               
                if(action !=null && action.equals("edit")){
            String userSelected=request.getParameter("user");
            Users selectedUser;
            try{
                selectedUser=us.get(userSelected);
                request.setAttribute("selectedUser", selectedUser);
            }
            catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        }
               

        try {
            HttpSession session = request.getSession();
            //String username = (String) session.getAttribute("username");
            List<Users> users = us.getAll();
            System.out.println(users);
                    
            request.setAttribute("users", users);
           
          
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

      
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        
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
          String promoteString=request.getParameter("promote");
       boolean active=Boolean.parseBoolean(activeString);

        boolean isAdmin=Boolean.parseBoolean(promoteString);
          String change =request.getParameter("selected");
       
         
System.out.println("hello two ");
        try {
            switch (action) {
                case "create":
                   
               
              
        
                    us.insert(username,password,email,firstName,lastName,active,isAdmin);
                
                    break;
                case "edit":
                   
                  
                 us.update( username,  password,  email,  firstName,  lastName,  active,  isAdmin);
                   
                    break;
                case "delete":
                    
                    
                    String user = request.getParameter("user");
                  
                    us.delete(user);
               break;
                case "search":
                    String Word = request.getParameter("searchText");
                    List<Items> items = is.getAllItems();
                    ArrayList<Items> search_List = new ArrayList<Items>();
                    System.out.println("in the search "+Word);
                    for(int i=0; i<items.size(); i++){
                       if( items.get(i).getItemName().contains(Word)){
                           search_List.add(items.get(i));
                       }
                    }
                    request.setAttribute("items",search_List );
            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(InventoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            List<Users> users = us.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(InventoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

  
}
