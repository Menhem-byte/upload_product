/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import java.util.List;
import models.Categories;
import models.Items;

/**
 *
 * @author 789438
 */
public class CategoryServices {
             public List<Categories> getAll() throws Exception {
       CategoryDB categorydb = new CategoryDB();
        List<Categories> categories = categorydb.getAll();
        return categories;
    }
             
                 public Categories get(int Categoryid) throws Exception {
        CategoryDB categorydb = new CategoryDB();
        Categories category = categorydb.get(Categoryid);
        return category;
    }
                 
                 
     public void insert(int Category,String categoryName) throws Exception{
                 
       Categories category = new Categories(Category,categoryName);
       CategoryDB categorydb=new CategoryDB();
     categorydb.insert(category);
     }
     
     public void update(int Category,String categoryName) throws Exception{
         CategoryDB categorydb=new CategoryDB();
     Categories category = categorydb.get(Category);
     category.setCategoryName(categoryName);
     categorydb.update(category);
         
     }
     
      public void delete(int Category) throws Exception{
         CategoryDB categorydb=new CategoryDB();
     Categories category = categorydb.get(Category);
    
     categorydb.delete(category);
         
     }
    
}
