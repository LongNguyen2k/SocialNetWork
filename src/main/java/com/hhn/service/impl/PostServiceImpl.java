/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.hhn.pojos.Post;
import com.hhn.repository.PostRepository;
import com.hhn.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows 10
 */
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Object[]> getPostsUserProfile(String kw) {
       return this.postRepository.getPostFromUser(kw);
    }

    @Override
    public List<Object[]> getPostNewFeed(String kw) {
        return this.postRepository.getNewFeedPost(kw);
    }

    @Override
    public List<Object[]> getPostFromCategoryPost(String kw,String id) {
        return this.postRepository.getPostFromCategoryPost(kw,id);
    }
    
    
    
}
