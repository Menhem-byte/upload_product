/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.List;
import models.Categories;
import models.Items;
import models.Users;

/**
 *
 * @author 789438
 */
public class ItemsServices {
        public Items get(int id) throws Exception {
        ItemDB ItemDB = new ItemDB();
        Items item = ItemDB.getItem(id);
        return item;
    }
      
      public List<Items> getAll(String username) throws Exception {
       ItemDB ItemDB = new ItemDB();
        List<Items> items = ItemDB.getAll(username);
        return items;
    }
       public List<Items> getAllItems() throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Items> arrItems = itemDB.getAll();
        return arrItems;
    }
      
      public void insert(int categoryID,String itemName,String  path,String filename, double price,String owner) throws Exception{
        
      Items item=new Items(0,itemName,price);
      UserDB userdb=new UserDB();
      Users user=userdb.get(owner);
     
    CategoryDB categorydb =new CategoryDB();
      Categories category=categorydb.get(categoryID);
       item.setOwner(user);
       item.setCategory(category);
       item.setPath(path);
       item.setFilename(filename);
      ItemDB itemdb=new ItemDB();
      itemdb.insert(item);
      }
      
      public void update(int categoryID,int itemID, String itemName,String  path,String filename, double price,String owner) throws Exception{
          ItemDB itemdb=new ItemDB();
       Items item=itemdb.getItem(itemID);
       CategoryDB categorydb=new CategoryDB();
      Categories category=categorydb.get(categoryID);
       item.setCategory(category);
       item.setItemName(itemName);
       item.setPrice(price);
        item.setPath(path);
       item.setFilename(filename);
       itemdb.update(item);
      }
      
      public void delete(int itemID) throws Exception{
      
          ItemDB itemdb=new ItemDB();
          Items item=itemdb.getItem(itemID);
          itemdb.delete(item);
      }
}
