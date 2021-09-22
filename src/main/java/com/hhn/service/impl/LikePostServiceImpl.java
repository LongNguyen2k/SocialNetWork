/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.hhn.pojos.LikePost;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.LikePostRepository;
import com.hhn.repository.PostRepository;
import com.hhn.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hhn.service.LikePostService;

/**
 *
 * @author Windows 10
 */
@Service
@Transactional
public class LikePostServiceImpl implements LikePostService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikePostRepository likePostRepository;
    @Autowired
    private PostRepository postRepository;
     @Override
    public boolean checkPostUserLike(String userNameLoggedIn, String postIdLike) {
        User userLoggedIn = this.userRepository.getCurrentLoggedInUser(userNameLoggedIn).get(0);
        Post postLikePost = this.postRepository.getPostId(postIdLike);
         List<LikePost> likePosts = this.likePostRepository.checklikePost(userLoggedIn, postLikePost);
        if(!likePosts.isEmpty())
            return true;
        return false;
    }

    
    
}
