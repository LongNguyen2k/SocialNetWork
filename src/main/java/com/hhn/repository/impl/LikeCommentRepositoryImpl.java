/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository.impl;

import com.hhn.pojos.Comments;
import com.hhn.pojos.LikeComment;
import com.hhn.pojos.LikePost;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.LikeCommentRepository;
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
public class LikeCommentRepositoryImpl implements LikeCommentRepository{
     @Autowired
     private LocalSessionFactoryBean sessionFactory;
    @Override
    public List<LikeComment> checkLikeComment(User userCurrentLoggedIn, Comments commentLike) {
      Session session = sessionFactory.getObject().getCurrentSession();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<LikeComment> query = builder.createQuery(LikeComment.class);
      Root<LikeComment> root = query.from(LikeComment.class);
      Predicate p1 = builder.equal(root.get("user").as(User.class),userCurrentLoggedIn);
      Predicate p2 = builder.equal(root.get("comment").as(Comments.class),commentLike);
      query.where(builder.and(p1,p2));
      query.select(root);
      Query q = session.createQuery(query);
      return q.getResultList();
    }
    
}
