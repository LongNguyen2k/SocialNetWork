/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.hhn.pojos.CategoryPost;
import com.hhn.repository.CategoryPostRepository;
import com.hhn.service.CategoryPostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryPostServiceImpl implements CategoryPostService{
    @Autowired
    private CategoryPostRepository CategoryPostRepository;
    @Override
    public List<CategoryPost> getCategories() {
        return this.CategoryPostRepository.getCategories();
    }
    
}
