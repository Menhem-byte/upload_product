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
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "ReportServlet", urlPatterns = {"/report"})
public class ReportServlet extends HttpServlet {

  
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

         getServletContext().getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         getServletContext().getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
       
    }

    
}
