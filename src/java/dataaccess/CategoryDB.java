/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Categories;

/**
 *
 * @author 789438
 */
public class CategoryDB {
      public List<Categories> getAll()throws Exception{
     EntityManager em=DBUtil.getEmFactory().createEntityManager();
       try{
       
        Query query=em.createNamedQuery("Categories.findAll");
             List<Categories> categories=query.getResultList();
             
            return categories;
        }
        
        finally{
        em.close();
        }
    
    }
      
      public void insert(Categories category) throws Exception{
      
          EntityManager   em=DBUtil.getEmFactory().createEntityManager();
          EntityTransaction trans=em.getTransaction();
          
          try{
          trans.begin();
          em.persist(category);
          trans.commit();
          }
          catch(Exception ex){
              trans.rollback();
          }
          finally{
          em.close();
          }
      }
      
      
      public void update(Categories category) throws Exception{
       EntityManager   em=DBUtil.getEmFactory().createEntityManager();
          EntityTransaction trans=em.getTransaction();
          try{
          trans.begin();
          em.merge(category);
          trans.commit();
          }
          catch(Exception ex){
          trans.rollback();
          }
          
          finally{
          em.close();
          }
      }

      public void delete(Categories category) throws Exception{
          EntityManager em= DBUtil.getEmFactory().createEntityManager();
          EntityTransaction trans = em.getTransaction();
            try{
          trans.begin();
          em.remove(em.merge(category)); 
          trans.commit();
          }
          catch(Exception ex){
          trans.rollback();
          }
          
          finally{
          em.close();
          }
          
      }
      
      
      
    public Categories get(int categoryID) {
        EntityManager em=DBUtil.getEmFactory().createEntityManager();
    try{
    Categories category=em.find(Categories.class, categoryID);
    
    return category;
    }
    finally{
        em.close();
    }
    }
    
    
}
