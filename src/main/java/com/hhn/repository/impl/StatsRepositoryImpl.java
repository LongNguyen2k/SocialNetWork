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
import com.hhn.pojos.ReportComment;
import com.hhn.pojos.ReportPost;
import com.hhn.repository.StatsRepository;
import java.util.ArrayList;
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
public class StatsRepositoryImpl implements StatsRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Override
    public List<Object[]> categoryPostStats() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root cRoot = query.from(CategoryPost.class);
        Root pRoot = query.from(Post.class);
        query.where(builder.equal(pRoot.get("categoryPost"), cRoot.get("id")));
        query.multiselect(cRoot.get("id") , cRoot.get("name") , 
                builder.count(pRoot.get("id")));
        query.groupBy(cRoot.get("id"));
        Query  q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public  List<Object[]> postStats(String kw,Date fromDate, Date toDate) {
        
        // Thống kê số lượng bài viết theo danh mục hỗ trợ tìm kiếm theo danh mục thời gian 
        
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root pRoot = query.from(Post.class);
        Root cRoot = query.from(CategoryPost.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(cRoot.get("id"),pRoot.get("categoryPost")));
        
        query.multiselect( builder.function("MONTH", Integer.class,pRoot.get("postAt")) , 
                 builder.function("YEAR", Integer.class,pRoot.get("postAt")), builder.count(pRoot.get("id")) ); 
        
        if(kw != null &&  !kw.isEmpty())
            predicates.add(builder.like(cRoot.get("name").as(String.class),String.format("%%%s%%" ,kw)));
        if(fromDate != null)
            predicates.add(builder.greaterThanOrEqualTo(pRoot.get("postAt"), fromDate));
        if(toDate != null)
            predicates.add(builder.lessThanOrEqualTo(pRoot.get("postAt"),toDate));
        
        query.where(predicates.toArray(new Predicate[]{}));
        query.groupBy(builder.function("MONTH", Integer.class,pRoot.get("postAt")) , 
                 builder.function("YEAR", Integer.class,pRoot.get("postAt")));
        Query  q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object[]> likeStats() {
       Session session = this.sessionFactory.getObject().getCurrentSession();
       CriteriaBuilder builder = session.getCriteriaBuilder();
       CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
       Root pRoot = query.from(Post.class);
       Root cRoot = query.from(CategoryPost.class);
       Root lRoot = query.from(LikePost.class);
       
       List<Predicate> predicates = new ArrayList<>();
       predicates.add(builder.equal(pRoot.get("categoryPost"),cRoot.get("id")));
       predicates.add(builder.equal(pRoot.get("id"),lRoot.get("post")));
       query.multiselect(cRoot.get("id") , cRoot.get("name") ,
               builder.count(lRoot.get("id")));
       query.where(predicates.toArray(new Predicate[]{}));
       query.groupBy(cRoot.get("id"));
       Query q = session.createQuery(query);
       return q.getResultList();
    }

    @Override
    public List<Object[]> commentsStats() {
       Session session = this.sessionFactory.getObject().getCurrentSession();
       CriteriaBuilder builder = session.getCriteriaBuilder();
       CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
       Root pRoot = query.from(Post.class);
       Root caRoot = query.from(CategoryPost.class);
       Root cRoot = query.from(Comments.class);
       List<Predicate> predicates = new ArrayList<>();
       predicates.add(builder.equal(pRoot.get("categoryPost"),caRoot.get("id")));
       predicates.add(builder.equal(pRoot.get("id"),cRoot.get("post")));
       query.multiselect(caRoot.get("id"),caRoot.get("name") , builder.count(cRoot.get("id")));
       query.where(predicates.toArray(new Predicate[]{}));
       query.groupBy(caRoot.get("id"));
       Query q = session.createQuery(query);
       return q.getResultList();
    }

    @Override
    public List<Object[]> commentDayMonthStat(String kw, Date fromDate, Date toDate) {
       Session session = this.sessionFactory.getObject().getCurrentSession();
       CriteriaBuilder builder = session.getCriteriaBuilder();
       CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
       
       Root pRoot = query.from(Post.class);
       Root caRoot = query.from(CategoryPost.class);
       Root cRoot = query.from(Comments.class);
       
       List<Predicate> predicates = new ArrayList<>();
       predicates.add(builder.equal(pRoot.get("categoryPost"),caRoot.get("id")));
       predicates.add(builder.equal(pRoot.get("id"),cRoot.get("post")));
       
       query.multiselect( builder.function("DAY", Integer.class,cRoot.get("postAt")) , 
                 builder.function("MONTH", Integer.class,cRoot.get("postAt")), builder.count(cRoot.get("id")) ); 
       
        if(kw != null && !kw.isEmpty())
             predicates.add(builder.like(caRoot.get("name").as(String.class),String.format("%%%s%%" ,kw)));
        if(fromDate != null)
            predicates.add(builder.greaterThanOrEqualTo(cRoot.get("postAt"), fromDate));
        if(toDate != null)
            predicates.add(builder.lessThanOrEqualTo(cRoot.get("postAt"),toDate));
        
        query.where(predicates.toArray(new Predicate[]{}));
        query.groupBy(builder.function("DAY", Integer.class,cRoot.get("postAt")) , 
                 builder.function("MONTH", Integer.class,cRoot.get("postAt")));
        Query q = session.createQuery(query);
         return q.getResultList();
    }

    @Override
    public List<Object[]> reportPostStats() {
       Session session = this.sessionFactory.getObject().getCurrentSession();
       CriteriaBuilder builder = session.getCriteriaBuilder();
       CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
       
       Root pRoot = query.from(Post.class);
       Root caRoot = query.from(CategoryPost.class);
       Root rPRoot = query.from(ReportPost.class);
       
       List<Predicate> predicates = new ArrayList<>();
       predicates.add(builder.equal(pRoot.get("categoryPost"),caRoot.get("id")));
       predicates.add(builder.equal(pRoot.get("id"),rPRoot.get("postReport")));
       query.multiselect(caRoot.get("id"),caRoot.get("name") , builder.count(rPRoot.get("id")));
       query.where(predicates.toArray(new Predicate[]{}));
       query.groupBy(caRoot.get("id"));
       Query q = session.createQuery(query);
       return q.getResultList();
    }

    @Override
    public List<Object[]> reportCommentStats() {
         Session session = this.sessionFactory.getObject().getCurrentSession();
       CriteriaBuilder builder = session.getCriteriaBuilder();
       CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
       
       Root pRoot = query.from(Post.class);
       Root caRoot = query.from(CategoryPost.class);
       Root cRoot = query.from(Comments.class);
       Root rPRoot = query.from(ReportComment.class);
       
       List<Predicate> predicates = new ArrayList<>();
       predicates.add(builder.equal(pRoot.get("categoryPost"),caRoot.get("id")));
       predicates.add(builder.equal(pRoot.get("id"),cRoot.get("post")));
       predicates.add(builder.equal(rPRoot.get("commentsReport"), cRoot.get("id")));
       query.multiselect(caRoot.get("id"),caRoot.get("name") , builder.count(rPRoot.get("id")));
       query.where(predicates.toArray(new Predicate[]{}));
       query.groupBy(caRoot.get("id"));
       Query q = session.createQuery(query);
       return q.getResultList();
    }
    
}
