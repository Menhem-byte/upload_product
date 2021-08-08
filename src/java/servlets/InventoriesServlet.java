/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.time.Clock.system;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.Categories;
import models.Items;
import models.Users;
import services.CategoryServices;
import services.ItemsServices;
import services.UsersServices;

/**
 *
 * @author 789438
 */
@WebServlet(name = "InventoriesServlet", urlPatterns = {"/inventory"})

@MultipartConfig(fileSizeThreshold=1024*1024*2,
        maxFileSize=1024*1024*10,
        maxRequestSize=1024*1024*50)
public class InventoriesServlet extends HttpServlet {
 private static final long serialVersionUID=1L;
    private static final String UPLOAD_DIR="images";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       
        
        ItemsServices is = new ItemsServices();
        CategoryServices cs=new CategoryServices();
         UsersServices us=new UsersServices();
        
        String action= request.getParameter("action");
        
        if(action!=null && action.equals("edit")){
            String itemSelected = request.getParameter("itemId"); 
         
          
            int itemSelectedId = Integer.parseInt(itemSelected);
            Items selectedItem;
            
            try {
                selectedItem=is.get(itemSelectedId);
               
                request.setAttribute("selectedItem",selectedItem );
                
            } catch (Exception ex) {
                Logger.getLogger(InventoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        //request.setAttribute("itemid",itemSelectedId );
     List<Categories> categories =null;
        try {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            Users user= us.get(username);
          
            List<Items> items = is.getAll(username);
            request.setAttribute("items", items);
            categories = cs.getAll();
            request.setAttribute("categories", categories);
           
        } catch (Exception ex) {
            Logger.getLogger(InventoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
         HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        ItemsServices is = new ItemsServices();
      System.out.println("hello one ");
        
        String action = request.getParameter("action");
        System.out.println("getAction = " + action);
      
      //  String category = request.getParameter("category");
        String itemName = request.getParameter("itemname");
        String price = request.getParameter("price");
        String categoryID=request.getParameter("category");
        String itemID;
            String applicationPath = request.getServletContext().getRealPath("");
       //String basePath="C:\\Users\\789438\\Desktop\\third semester\\web application\\Assignments\\assignment2\\assignment-2-Menhem-byte\\Assignment2\\web\\images"+File.separator+UPLOAD_DIR;
            //File.separator: \ 
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
       String filename=uploadFile(request);
      
              
         if(filename.equals("") || filename==null){
           basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
           filename="default.jpg";
         }
    
        
System.out.println("hello two ");
        try {
            switch (action) {
                case "create":
                    System.out.println("hello three ");
                     //System.out.println();
                    System.out.println(categoryID);
                    is.insert(Integer.parseInt(categoryID), itemName,basePath,filename, Double.parseDouble(price), username);
                 
                    
                    break;
                 case "edit":
                     String pathfile=basePath+filename;
                    itemID = request.getParameter("itemId");
                    if(filename==null && filename.equals("")){
                           basePath=is.get( Integer.parseInt(itemID)).getPath();
                    filename=is.get( Integer.parseInt(itemID)).getFilename();
                   request.setAttribute("file",pathfile);
                    }
                    
                  request.setAttribute("file",pathfile);
                    
                  is.update(Integer.parseInt(categoryID), Integer.parseInt(itemID), itemName,basePath,filename,Double.parseDouble(price), username);
                
                   
                    break;
                case "delete":
                     itemID = request.getParameter("itemId");
                    is.delete(Integer.parseInt(itemID));
                    break;
               
                    
            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(InventoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            List<Items> items = is.getAll(username);
            request.setAttribute("items", items);
        } catch (Exception ex) {
            Logger.getLogger(InventoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
       
    }
     private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("file");

            //fileName: picture-001.jpg
            fileName = (String) getFileName(filePart);
//System.out.println("the path is "+basePath);
//System.out.println("the filename is"+fileName);
            //applicationPath: C:\Users\Lonely\Documents\NetBeansProjects\Shop_Bonfire\build\web
            String applicationPath = request.getServletContext().getRealPath("");
       //String basePath="C:\\Users\\789438\\Desktop\\third semester\\web application\\Assignments\\assignment2\\assignment-2-Menhem-byte\\Assignment2\\web\\images"+File.separator+UPLOAD_DIR;
            //File.separator: \ 
           String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
           
          
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }
 
  private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
 
 
    
    }


