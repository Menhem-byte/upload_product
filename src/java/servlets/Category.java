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
import models.Categories;
import models.Users;
import services.CategoryServices;

/**
 *
 * @author 789438
 */
public class Category extends HttpServlet {

  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CategoryServices cs = new CategoryServices();
        String action= request.getParameter("action");
        
        if(action !=null && action.equals("edit")){
            String categorySelected = request.getParameter("categoryid");
            int categoryid=Integer.parseInt(categorySelected);
            Categories selectedCategory;
           
            try {
                selectedCategory=cs.get(categoryid);
                request.setAttribute("selectedCategory",selectedCategory );
            } catch (Exception ex) {
                Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
         try {
            HttpSession session = request.getSession();
            //String username = (String) session.getAttribute("username");
            List<Categories> categories = cs.getAll();
            System.out.println(categories);
                    
            request.setAttribute("categories", categories);
           
          
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
          getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CategoryServices cs = new CategoryServices();
        
        String action = request.getParameter("action");
        String categoryName=request.getParameter("categoryname");
        String categoryID=request.getParameter("categoryid");
          try{
        switch(action){
      
            case "create":
        {
            try {
                cs.insert(Integer.parseInt(categoryID), categoryName);
            } catch (Exception ex) {
                Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
                
              case "edit":
        {
            try {
                cs.update(Integer.parseInt(categoryID), categoryName);
            } catch (Exception ex) {
                Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                  break;
                  
                  
           case "delete":  
               
               cs.delete(Integer.parseInt(categoryID));
              
               break;
        }
         request.setAttribute("message", action);
    }catch (Exception ex) {
            Logger.getLogger(InventoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
         try {
            List<Categories> categories = cs.getAll();
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(InventoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
         getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
    }

  
}
