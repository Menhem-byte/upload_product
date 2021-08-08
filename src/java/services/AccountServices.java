/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import models.Users;

/**
 *
 * @author 789438
 */
public class AccountServices {
      UserDB userDB = new UserDB();
     
    public Users login(String username, String password) {
        
        
        try {
            Users user =  userDB.get(username);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }

    public Users getAccount(String username) {
        try{
       Users user = userDB.get(username);
        return user;
        }
        catch(Exception e){
         return null;
        }
    }
}
