/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository.impl;

import com.hhn.pojos.Auctions;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.AuctionRespository;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
public class AuctionRepositoryImpl implements AuctionRespository{
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Object[]> getBiddingInfo(int postId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Post> pRoot = query.from(Post.class);
        Root<User> uRoot = query.from(User.class);
        Predicate p1 = builder.equal(pRoot.get("id"),postId);
        Predicate p2 = builder.equal(pRoot.get("user"),uRoot.get("id"));
        query.where(builder.and(p1,p2));
        query.multiselect(uRoot.get("avatar").as(String.class) , 
                        uRoot.get("name").as(String.class) ,
                        uRoot.get("phoneNumber").as(String.class) , 
                        uRoot.get("gender").as(String.class) ,
                        uRoot.get("birthday").as(java.sql.Date.class) ,
                        uRoot.get("email").as(String.class) ,
                        pRoot.get("content").as(String.class) ,
                        pRoot.get("postAt").as(java.sql.Date.class) , 
                        pRoot.get("image").as(String.class) , 
                        pRoot.get("startprice").as(BigDecimal.class) , 
                        pRoot.get("likes") , 
                        pRoot.get("id") ,
                        uRoot.get("username").as(String.class)
                        );
         Query<Object[]> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean addBiddingPrice(Auctions actns) {
       Session session = sessionFactory.getObject().getCurrentSession();
       try{
           session.save(actns);
           return true;
       }
       catch(HibernateException ex)
       {
           System.err.println("== Add new Auction Bidding Have Error " + ex.getMessage());
           ex.printStackTrace();
       }
       return false;
    }

    @Override
    public List<Auctions> getListOfBiddingFromPost(Post post) {
       Session session =  sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Auctions> query = builder.createQuery(Auctions.class);
        Root<Auctions> aRoot = query.from(Auctions.class);
        Predicate p1 = builder.equal(aRoot.get("biddingPost").as(Post.class),post);
        query.where(p1);
        query.select(aRoot);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public BigDecimal getMaxAuctionPrice(Post post) {
        Session session =  sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);
        Root<Auctions> aRoot = query.from(Auctions.class);
        Predicate p1 = builder.equal(aRoot.get("biddingPost").as(Post.class),post);
        query.where(p1);
        query.select(builder.max(aRoot.get("biddingPrice").as(BigDecimal.class)));
        Query<BigDecimal> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Override
    public boolean chooseWiner(Auctions auctions) {
         Session session = sessionFactory.getObject().getCurrentSession();
       try{
           session.update(auctions);
           return true;
       }
       catch(HibernateException ex)
       {
           System.err.println("== Choose Winner Have Encountered Error " + ex.getMessage());
           ex.printStackTrace();
       }
       return false;
    }

    @Override
    public Auctions findAuctionMethod(User user , Post post , BigDecimal priceBidding , Timestamp timestamp) {
       Session session =  sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Auctions> query = builder.createQuery(Auctions.class);
       Root<Auctions> aRoot = query.from(Auctions.class);
        Predicate p1 = builder.equal(aRoot.get("biddingPost").as(Post.class),post);
        Predicate p2 = builder.equal(aRoot.get("biddingUser").as(User.class),user);
        Predicate p3 = builder.equal(aRoot.get("biddingPrice").as(BigDecimal.class),priceBidding);
        Predicate p4 = builder.equal(aRoot.get("biddingAt").as(Timestamp.class),timestamp);
        query.where(builder.and(p1,p2,p3,p4));
        query.select(aRoot);
        Query<Auctions> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Override
    public List<Auctions> findAuctionAlreadyWinner(Post post) {
         Session session =  sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Auctions> query = builder.createQuery(Auctions.class);
       Root<Auctions> aRoot = query.from(Auctions.class);
        Predicate p1 = builder.equal(aRoot.get("biddingPost").as(Post.class),post);
        Predicate p2 = builder.equal(aRoot.get("biddingStatus").as(Boolean.class),true);
        query.where(builder.and(p1,p2));
        query.select(aRoot);
        Query<Auctions> q = session.createQuery(query);
        return q.getResultList();
    }
    
    
    
  
    
}
