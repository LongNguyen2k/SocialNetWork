/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository;

import com.hhn.pojos.Comments;
import com.hhn.pojos.LikeComment;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface CommentsRepository {
    boolean addComment(Comments comments);
     List<Object[]> getCommentFromPost(int postId);
     Long countCommentList(int postId);
    Comments getCommentsById(String CommentId);
     boolean likeComment(Comments commentsLike , LikeComment likeComment);
     boolean unLikeComment(Comments commentsLike , LikeComment likeComment);
}
