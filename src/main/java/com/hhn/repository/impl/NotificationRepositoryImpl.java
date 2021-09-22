/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository.impl;

import com.hhn.pojos.Notifications;
import com.hhn.pojos.User;
import com.hhn.repository.NotitificationRepository;
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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Repository
@Transactional
public class NotificationRepositoryImpl  implements NotitificationRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Override
    public List<Object[]> getListNotificationses(User userReceiVeNotify) {
       Session session =  sessionFactory.getObject().getCurrentSession();
       CriteriaBuilder builder = session.getCriteriaBuilder();
       CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
       Root<Notifications> nRoot = query.from(Notifications.class);
       Root<User> uRoot = query.from(User.class);
        Predicate p1 = builder.equal(nRoot.get("receiverUser"),userReceiVeNotify);
        Predicate p2 = builder.equal(uRoot.get("id"),nRoot.get("senderUser"));
        query.where(builder.and(p1,p2));
        query.multiselect(uRoot.get("avatar").as(String.class) , 
                          uRoot.get("name").as(String.class) , 
                          nRoot.get("type"));
        query.orderBy(builder.desc((nRoot.get("id"))));
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean addNotifications(Notifications notifications) {
       Session session = sessionFactory.getObject().getCurrentSession();
       try{
           session.save(notifications);
           return true;
       }catch(HibernateException ex)
       {
           System.err.println("== Add Notification having error " + ex.getMessage());
           ex.printStackTrace();
       }
       return false;
    }
    
}
