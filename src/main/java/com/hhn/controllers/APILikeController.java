/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.pojos.LikePost;
import com.hhn.pojos.Notifications;
import com.hhn.service.LikePostService;
import com.hhn.service.NotificationService;
import com.hhn.service.PostService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Windows 10
 */
@RestController
public class APILikeController {
    @Autowired
    private LikePostService likePostService;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private NotificationService notificationService;
    
    @PostMapping(path = "/user/api/addLikeOrUnLike",produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LikePost> addLikeOrUnLIke(@RequestBody Map<String,String> params)
    {
        try{
        String  userLoggedInName = params.get("username");
        String likedPostId = params.get("post_id");
        LikePost likePost = new LikePost();
        Notifications n = new Notifications();
         if( likePostService.checkPostUserLike(userLoggedInName, likedPostId) == true )
         {
             if(this.postService.unLikePost(userLoggedInName,likedPostId) == true)
                 return new ResponseEntity<>(likePost,HttpStatus.OK);
         }
         else
         {
              if(this.postService.likePost(userLoggedInName,likedPostId ,  likePost) == true)
                {
                    
                     n.setType(1);
                      this.notificationService.addNotifications(userLoggedInName, likedPostId, n);
                   return new ResponseEntity<>(likePost,HttpStatus.CREATED);
                }
         }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
