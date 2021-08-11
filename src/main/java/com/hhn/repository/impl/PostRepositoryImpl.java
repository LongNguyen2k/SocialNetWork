/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository.impl;

import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.PostRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class PostRepositoryImpl implements  PostRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Override
    public List<Object[]> getPostFromUser() {
        Session session =  sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        
        Root<Post> pRoot = query.from(Post.class);
        Root<User> uRoot = query.from(User.class);
        
        Predicate p1 = builder.equal(pRoot.get("user"), uRoot.get("id"));        
        int idUser = 1 ;
        Predicate p2 = builder.equal(pRoot.get("user") , idUser );
        query.where(builder.and(p1,p2));
        query.multiselect(uRoot.get("name").as(String.class), 
                          pRoot.get("content").as(String.class) , 
                          pRoot.get("postAt").as(Date.class) , 
                          pRoot.get("addressPost").as(String.class) , 
                          pRoot.get("likes") );
        Query<Object[]> q = session.createQuery(query);
        return q.getResultList();
        
    }
    
}
