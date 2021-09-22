/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository.impl;

import com.hhn.pojos.CategoryPost;
import com.hhn.pojos.Comments;
import com.hhn.pojos.LikePost;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.PostRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;

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
    public List<Object[]> getPostFromUser(String kw , String username) {
        Session session =  sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        
        Root<Post> pRoot = query.from(Post.class);
        Root<User> uRoot = query.from(User.class);
        
        Predicate p1 = builder.equal(pRoot.get("user"), uRoot.get("id"));        ;
        Predicate p2 = builder.equal(uRoot.get("username") , String.format("%s", username) );
        Predicate p3 =  builder.like( pRoot.get("content").as(String.class) ,"%%");
        if(!kw.isEmpty() && kw != null){
              p3 = builder.like( pRoot.get("content").as(String.class) , String.format("%%%s%%" ,kw));
        }
        query.where(builder.and(p1,p2,p3));
        query.multiselect(uRoot.get("avatar").as(String.class),
                          uRoot.get("name").as(String.class), 
                          pRoot.get("image").as(String.class),
                          pRoot.get("content").as(String.class) , 
                          pRoot.get("postAt").as(java.sql.Date.class) , 
                          pRoot.get("likes") ,
                           pRoot.get("id") );
        Query<Object[]> q = session.createQuery(query);
        return q.getResultList();
        
    }

    @Override
    public List<Object[]> getNewFeedPost(String kw , int page ) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        // display the post 
        Root<Post> pRoot = query.from(Post.class);
        Root<User> uRoot = query.from(User.class);  
      
        Predicate p1 = builder.equal(pRoot.get("user"), uRoot.get("id"));  
        Predicate p2 =  builder.like( pRoot.get("content").as(String.class) ,"%%");
//          Root<Comments> cRoot = query.from(Comments.class);
//        Predicate p3 = builder.equal(pRoot.get("id"),cRoot.get("post"));
        if(!kw.isEmpty() && kw != null ){
              p2 = builder.like( pRoot.get("content").as(String.class) , String.format("%%%s%%" ,kw));
        }
//        cRoot.get("comment").as(String.class),
//        cRoot.get("user").as(User.class)
        query.where(builder.and(p1,p2));
        query.multiselect(uRoot.get("avatar").as(String.class),
                          uRoot.get("name").as(String.class), 
                          pRoot.get("image").as(String.class),
                          pRoot.get("content").as(String.class) , 
                          pRoot.get("postAt").as(java.sql.Date.class) , 
                          pRoot.get("likes") ,
                           pRoot.get("id") 
//                          , pRoot.get("comments").as(Comments.class)
                          );
        query.orderBy(builder.desc(pRoot.get("id")));
        Query<Object[]> q = session.createQuery(query);
        int max = 3;
        q.setMaxResults(max);
        q.setFirstResult((page - 1) * max );

        return q.getResultList();
    }

    @Override
    public List<Object[]> getPostFromCategoryPost(String kw,String id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        
        Root<Post> pRoot = query.from(Post.class);
        Root<CategoryPost> cRoot = query.from(CategoryPost.class);
        Root<User> uRoot = query.from(User.class);
        Predicate p1 = builder.equal(pRoot.get("categoryPost"), cRoot.get("id"));
        Predicate p2 = builder.equal(pRoot.get("user"),uRoot.get("id"));
        Predicate p3 = builder.like(cRoot.get("id").as(String.class),"%%" );
        Predicate p4 = builder.like( pRoot.get("content").as(String.class) ,"%%");
        if(!id.isEmpty() && id != null ){
              p3 = builder.like( cRoot.get("id").as(String.class) , String.format("%%%s%%" ,id));
        }
        if(!kw.isEmpty() && kw != null ){
              p4 = builder.like( pRoot.get("content").as(String.class) , String.format("%%%s%%" ,kw));
        }
        query.where(builder.and(p1,p2,p3,p4));
        query.multiselect(uRoot.get("avatar").as(String.class),
                          uRoot.get("name").as(String.class), 
                          pRoot.get("image").as(String.class),
                          pRoot.get("content").as(String.class) , 
                          pRoot.get("postAt").as(java.sql.Date.class) , 
                          pRoot.get("likes") ,
                           pRoot.get("id")  );
                         
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    
 

    @Override
    public long countPost() {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT Count(*) FROM Post");
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public boolean addNewPost(Post post) {
        Session session = sessionFactory.getObject().getCurrentSession();
         try{
       
        session.save(post);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Add New Post Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean updatePost(Post post) {
        Session session = sessionFactory.getObject().getCurrentSession();
         try{
       
        session.update(post);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Update  Post Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
    

    @Override
    public Post getPostId(String postId) {
        Session session = sessionFactory.getObject().getCurrentSession();
       int id =  Integer.parseInt(postId);
        return session.get(Post.class,id);
       
    }

    @Override
    public boolean deletePost(Post post) {
        Session session = sessionFactory.getObject().getCurrentSession();
         try{
       
        session.delete(post);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Delete  Post Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean likePost(Post postLike , LikePost likePost) {
        Session session = sessionFactory.getObject().getCurrentSession();
        
         try{
       session.save(likePost);
       session.update(postLike);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Add like having  Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unLikePost(Post postLike , LikePost likePost) {
       Session session = sessionFactory.getObject().getCurrentSession();
        
         try{
       session.delete(likePost);
       session.update(postLike);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Delete like having  Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

   
    
    

   

   
    
    
   
  
    
}
