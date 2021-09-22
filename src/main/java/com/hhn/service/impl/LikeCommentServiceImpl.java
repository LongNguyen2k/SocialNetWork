/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.hhn.pojos.Comments;
import com.hhn.pojos.LikeComment;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.CommentsRepository;
import com.hhn.repository.LikeCommentRepository;
import com.hhn.repository.UserRepository;
import com.hhn.service.LikeCommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Service
@Transactional
public class LikeCommentServiceImpl implements LikeCommentService{
    @Autowired
    private CommentsRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikeCommentRepository likeCommentRepository;
    @Override
    public boolean checkCommentUserLike(String userNameLoggedIn, String commentIdLike) {
        User userLoggedIn = this.userRepository.getCurrentLoggedInUser(userNameLoggedIn).get(0);
        Comments commentUserLike = this.commentRepository.getCommentsById(commentIdLike);
        List<LikeComment>  likeComments = this.likeCommentRepository.checkLikeComment(userLoggedIn, commentUserLike);
        if(!(likeComments.isEmpty()))
            return true;
        else
            return false;
    }
    
}
