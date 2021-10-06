/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hhn.pojos.LikePost;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.LikePostRepository;
import com.hhn.repository.PostRepository;
import com.hhn.repository.UserRepository;
import com.hhn.service.PostService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Service
@Transactional
public class PostServiceImpl implements PostService{
    
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikePostRepository likePostRepository;
    @Override
    public List<Object[]> getPostsUserProfile(String kw , String username) {
       return this.postRepository.getPostFromUser(kw,username);
    }

    @Override
    public List<Object[]> getPostNewFeed(String kw , int page) {
        return this.postRepository.getNewFeedPost(kw,page);
    }

    @Override
    public List<Object[]> getPostFromCategoryPost(String kw,String id) {
        return this.postRepository.getPostFromCategoryPost(kw,id);
    }

    @Override
    public long countPost() {
       return this.postRepository.countPost();
    }
    
    
     @Override
    public Post getPostId(String postId) {
       return this.postRepository.getPostId(postId);
    }

    @Override
    public boolean addNewPost(Post post ) {
        Date date = new Date();
        post.setPostAt(date);
        post.setLikes(Post.likeCount);
        post.setCheckReported(false);
        // kiểm tra Content xem có chứa 1 đoạn hashtag nào không 
        
        if(post.getFile().getSize() == 0 )
        {
            post.setImage("");
             return this.postRepository.addNewPost(post);
        }
        else
        {
            try {
                Map r = this.cloudinary.uploader().upload(post.getFile().getBytes(),
                       ObjectUtils.asMap("resource_type","auto"));
                post.setImage((String) r.get("secure_url"));
                return this.postRepository.addNewPost(post);
            } catch (IOException ex) {
               System.out.println("Add Post" + ex.getMessage());
            }
        }
        return false;
    }

   

    @Override
    public boolean updatePost(Post post) {
       Date date = new Date();
        post.setPostAt(date);
        if(post.getFile().getSize() == 0   )
        {   
            return this.postRepository.updatePost(post);
        }
        else
        {
            try {
                Map r = this.cloudinary.uploader().upload(post.getFile().getBytes(),
                       ObjectUtils.asMap("resource_type","auto"));
                post.setImage((String) r.get("secure_url"));
                return this.postRepository.updatePost(post);
            } catch (IOException ex) {
               System.out.println("Update Post" + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deletePost(Post post) {
        return this.postRepository.deletePost(post);
    }

    @Override
    public boolean likePost(String username, String postId,LikePost likePost) {
       
       User userLikePost = this.userRepository.getCurrentLoggedInUser(username).get(0);
       Post postUserLike = this.postRepository.getPostId(postId);
       int currentPostLikes = postUserLike.getLikes() + 1;
        likePost.setPost(postUserLike);
        likePost.setUser(userLikePost);
       postUserLike.setLikes(currentPostLikes);
       return this.postRepository.likePost(postUserLike,likePost);
       
    }

    @Override
    public boolean unLikePost(String username, String postId) {
         User userLikePost = this.userRepository.getCurrentLoggedInUser(username).get(0);
       Post postUserLike = this.postRepository.getPostId(postId);
       int currentPostLikes = postUserLike.getLikes() - 1;
        postUserLike.setLikes(currentPostLikes);
        LikePost likePost = this.likePostRepository.checklikePost(userLikePost, postUserLike).get(0);
        return this.postRepository.unLikePost(postUserLike,likePost);
    }
    
    
    
}
