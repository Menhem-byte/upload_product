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
import models.Users;

/**
 *
 * @author 789438
 */
public class UserDB {
      public Users get(String username){
    EntityManager em=DBUtil.getEmFactory().createEntityManager();
    try{
    Users user=em.find(Users.class, username);
    return user;
    }
    finally{
        em.close();
    }
    }
    
     public List<Users> getAll()throws Exception{
     EntityManager em=DBUtil.getEmFactory().createEntityManager();
       try{
       
        Query query=em.createNamedQuery("Users.findAll");
             List<Users> users=query.getResultList();
             
            return users;
        }
        
        finally{
        em.close();
        }
    
    }
     
       public void insert(Users user) throws Exception {
    
    EntityManager em=DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans =em.getTransaction();
    try{
        
        trans.begin();
        em.persist(user);
        
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
       
         public void update(Users user) throws Exception{
      EntityManager em=DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans =em.getTransaction();
    try{
      
           trans.begin();
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
         
         
         
    public void delete(Users user) throws Exception{
  EntityManager em=DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans =em.getTransaction();
    try{
       
      trans.begin();
      em.remove(em.merge(user));
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
