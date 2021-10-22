/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.pojos.Comments;
import com.hhn.pojos.Notifications;
import com.hhn.service.CommentsService;
import com.hhn.service.NotificationService;
import java.net.URI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Windows 10
 */
@RestController
public class APICommentController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private NotificationService notificationService;
    
    @PostMapping(path = "/user/api/add-comment",produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Comments> addComment(@RequestBody Map<String,String> params)
    {
        try{
            String content = params.getOrDefault("content", "");
            int postID = Integer.parseInt(params.get("post_id"));
            String usernameLoggedIn =  params.get("username");
            Comments c = this.commentsService.apiAddComments(content, usernameLoggedIn, postID);
            Notifications n = new Notifications();
             n.setType(2);
            this.notificationService.addNotifications(usernameLoggedIn, params.get("post_id"), n);
            return new ResponseEntity<>(c,HttpStatus.CREATED);
        
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
} 