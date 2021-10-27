/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository.impl;

import com.hhn.pojos.LikePost;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.LikePostRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class LikePostRepositoryImpl implements LikePostRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Override
    public List<LikePost> checklikePost(User userCurrentLoggedIn , Post postLike) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LikePost> query = builder.createQuery(LikePost.class);
        Root<LikePost> root = query.from(LikePost.class);
        Predicate p1 = builder.equal(root.get("user").as(User.class),userCurrentLoggedIn);
        Predicate p2 = builder.equal(root.get("post").as(Post.class),postLike);
        query.where(builder.and(p1,p2));
        query.select(root);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<LikePost> getLikePostsFromPost(Post post) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LikePost> query = builder.createQuery(LikePost.class);
        Root<LikePost> root = query.from(LikePost.class);
        Predicate p = builder.equal(root.get("post").as(Post.class),post);
        query.where(p);
        query.select(root);
        Query q = session.createQuery(query);
        return q.getResultList();
    }


    
}
