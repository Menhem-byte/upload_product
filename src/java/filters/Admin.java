/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Users;
import services.AccountServices;

/**
 *
 * @author 789438
 */
public class Admin implements Filter {
    

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
            HttpServletRequest httpRequest=(HttpServletRequest) request;
      HttpSession session=httpRequest.getSession();
      String username= (String) session.getAttribute("username");
      AccountServices account =new AccountServices();
      Users user = null;
        try {
            user = account.getAccount(username);
        } catch (Exception ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!user.getIsAdmin()==true){
     HttpServletResponse httpResponse =(HttpServletResponse) response;
      httpResponse.sendRedirect("inventory");
      return;
      }
            chain.doFilter(request, response);
      
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      
    }

    @Override
    public void destroy() {
       
    }

  
}
