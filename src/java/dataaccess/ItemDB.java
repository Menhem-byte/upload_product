/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import models.Items;
import models.Users;

/**
 *
 * @author 789438
 */
public class ItemDB implements Serializable {
     public List<Items> getAll(String owner)throws Exception{
     EntityManager em=DBUtil.getEmFactory().createEntityManager();
       try{
           Users user=em.find(Users.class, owner);
            return user.getItemsList();
        }
        
        finally{
        em.close();
        }
    
    }
     
          public List<Items> getAll()throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
            
        try {
            List<Items> arrItems = em.createNamedQuery("Items.findAll", Items.class).getResultList();
            return arrItems;
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        } 
    }
    
     
    public Items getItem(int itemID){
    
        EntityManager em=DBUtil.getEmFactory().createEntityManager();
        try{
            Items item=em.find(Items.class, itemID);
            return item;
        }
        
        finally{
        em.close();
        }
    }
    
  
    
    public void insert(Items item) throws Exception {
   
    EntityManager em=DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans =em.getTransaction();
    trans.begin();
     System.out.println(item);
    try{
        Users user=item.getOwner();
        
       // trans.begin();
        em.persist(item);
        em.merge(user);
        trans.commit();
        user.getItemsList().add(item);
    }
    catch(Exception ex){
    trans.rollback();
    }
    finally
    {
    em.close();
    }
    }
    
    public void update(Items item) throws Exception{
      EntityManager em=DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans =em.getTransaction();
    try{
      System.out.println(item);
           trans.begin();
           em.merge(item);
           trans.commit();
    }
    catch(Exception ex){
    trans.rollback();
    }
    finally
    {
    em.close();
    }
    }
    
    
    public void delete(Items item) throws Exception{
   EntityManager em=DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans =em.getTransaction();
    try{
        Users user=item.getOwner();
        user.getItemsList().remove(item);
        trans.begin();
        em.remove(em.merge(item));
        em.merge(user);
        trans.commit();
    }
    catch(Exception ex){
    trans.rollback();
    }
    finally
    {
    em.close();
    }
    }
}
