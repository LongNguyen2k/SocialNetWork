/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository.impl;

import com.hhn.pojos.CategoryPost;
import com.hhn.repository.CategoryPostRepository;
import java.util.List;
import javax.persistence.Query;
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
public class CategoryPostRepositoryImpl implements  CategoryPostRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<CategoryPost> getCategories() {
      Session s = sessionFactory.getObject().getCurrentSession();
      Query q = s.createQuery("FROM CategoryPost");
       return q.getResultList();
    }
    
}
