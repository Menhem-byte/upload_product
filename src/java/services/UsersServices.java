/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.List;
import models.Items;
import models.Users;

/**
 *
 * @author 789438
 */
public class UsersServices {
     public Users get(String  username) throws Exception {
        UserDB userdb = new UserDB();
        Users user = userdb.get(username);
        return user;
    }
      
       public List<Users> getAll() throws Exception {
       UserDB userdb = new UserDB();
        List<Users> users = userdb.getAll();
        return users;
    }
       
       public void insert(String username, String password, String email, String firstName, String lastName, boolean active, boolean isAdmin) throws Exception{
      Users user=new Users(username,password,email,firstName,lastName,active,isAdmin);
      UserDB userdb=new UserDB();
 
      userdb.insert(user);
      }
       
       
      
      public void update(String username, String password, String email, String firstName, String lastName, boolean active, boolean isAdmin) throws Exception{
          UserDB userdb=new UserDB();
       Users user=userdb.get(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
         user.setActive(active);
         user.setIsAdmin(isAdmin);
      userdb.update(user);
      }
      
     public void delete(String username) throws Exception{
       
            UserDB userdb=new UserDB();
          Users user=userdb.get(username);
             ItemDB itemdb=new ItemDB();
      Items item;
       List<Items> items = itemdb.getAll(username);
        System.out.println(items+"  "+user);
     for(int i=0;i<items.size();i++){
         item=itemdb.getItem(items.get(i).getItemID());
         System.out.println(item);
      itemdb.delete(item);
     }
          userdb.delete(user);
      }
     
}
