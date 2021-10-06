/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository.impl;

import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.UserRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
   
    @Override
    public List<User> getUserProfile(String userName) {
         Session session =  sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> uRoot = query.from(User.class);    
        Predicate p1 = builder.equal(uRoot.get("username").as(String.class) , String.format("%s",userName) );
        query.where(p1);
        query.select(uRoot);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean addUser(User user) { 
        Session session = this.sessionFactory.getObject().getCurrentSession();
         try{
       
        session.save(user);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Add User Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getUsers(String username) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> uRoot = query.from(User.class);
        if(!username.isEmpty()){
            Predicate p = builder.equal(uRoot.get("username").as(String.class),username.trim());
            query.where(p);
        }
        query.select(uRoot);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public  List<User> checkUserName(String username) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> uRoot = query.from(User.class);
            Predicate p = builder.equal(uRoot.get("username").as(String.class),username.trim());
            query.where(p);
        query.select(uRoot);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<User> getUserIdLoggedIn(String userId) {
         Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> uRoot = query.from(User.class);
            Predicate p = builder.equal(uRoot.get("id").as(String.class),userId.trim());
            query.where(p);
        query.select(uRoot);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<User> getCurrentLoggedInUser(String username) {
       Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> uRoot = query.from(User.class);
            Predicate p = builder.equal(uRoot.get("username").as(String.class),username.trim());
            query.where(p);
        query.select(uRoot);
        Query q = session.createQuery(query);
        return q.getResultList();
      
    }

    @Override
    public User getUserID(String id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        int Userid = Integer.parseInt(id);
        return session.get(User.class,Userid);
    }

    @Override
    public List<User> getUserAdminRole() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> uRoot = query.from(User.class);
        Predicate p = builder.equal(uRoot.get("uRole").as(String.class),"ROLE_ADMIN");
        query.where(p);
        query.select(uRoot);
        Query q = session.createQuery(query);
        return q.getResultList();
    }
    
    

 
}
