/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository.impl;

import com.hhn.pojos.CategoryPost;
import com.hhn.pojos.Post;
import com.hhn.repository.StatsRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    
}
