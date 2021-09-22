/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository;

import com.hhn.pojos.Comments;
import com.hhn.pojos.LikeComment;
import com.hhn.pojos.User;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface LikeCommentRepository {
    List<LikeComment> checkLikeComment(User userCurrentLoggedIn ,Comments commentLike);
}
