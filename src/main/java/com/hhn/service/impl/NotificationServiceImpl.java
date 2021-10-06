/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.hhn.pojos.Comments;
import com.hhn.pojos.Notifications;
import com.hhn.pojos.Post;
import com.hhn.repository.NotitificationRepository;
import com.hhn.service.NotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hhn.pojos.User;
import com.hhn.repository.CommentsRepository;
import com.hhn.repository.PostRepository;
import com.hhn.repository.UserRepository;
/**
 *
 * @author Windows 10
 */
@Service
@Transactional
public class NotificationServiceImpl  implements NotificationService{
    @Autowired
    private NotitificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
     private CommentsRepository commentsRepository;
    @Override
    public List<Object[]> getNotificationFromUser(String UserName) {
        User userReceiVeNotify = this.userRepository.getCurrentLoggedInUser(UserName).get(0);
       return this.notificationRepository.getListNotificationses(userReceiVeNotify);
    }

    @Override
    public boolean addNotifications(String userCurrentLoggedInName , String postIdHaveUser ,  Notifications notifications) {
        User userAction = this.userRepository.getCurrentLoggedInUser(userCurrentLoggedInName).get(0);
        int notificationsType = notifications.getType();
        Post postUser;
        Comments commentUser;
        
        switch (notificationsType)
        {
         case 1:
            {
                postUser = this.postRepository.getPostId(postIdHaveUser);
                User userCreatePost = postUser.getUser();
                notifications.setReceiverUser(userCreatePost);
                notifications.setSenderUser(userAction);
                break;
            }
            case 2:
            {
                postUser = this.postRepository.getPostId(postIdHaveUser);
                User userCreatePost = postUser.getUser();
                notifications.setReceiverUser(userCreatePost);
                notifications.setSenderUser(userAction);
                break;
            }
              case 3:
              {
                commentUser = this.commentsRepository.getCommentsById(postIdHaveUser);
                User userCreateComment = commentUser.getUser();
                notifications.setReceiverUser(userCreateComment);
                notifications.setSenderUser(userAction);
                break;
              } 
              case 4:
              { 
                  // ra giá 
                postUser = this.postRepository.getPostId(postIdHaveUser);
                User userCreatePost = postUser.getUser();
                notifications.setReceiverUser(userCreatePost);
                notifications.setSenderUser(userAction);
                break;
              }
              
               case 5:
              { 
                  // chon người chiến thắng 
                postUser = this.postRepository.getPostId(postIdHaveUser);
                User userCreatePost = postUser.getUser();
                notifications.setReceiverUser(userAction);
                notifications.setSenderUser(userCreatePost);
                break;
              }
              
              
              
        }     
       return this.notificationRepository.addNotifications(notifications);
    }

    @Override
    public boolean addNotificationAdmin(User adminSendReport , User userReceiveReport , Notifications notifications) {
        notifications.setSenderUser(adminSendReport);
        notifications.setReceiverUser(userReceiveReport);
        return this.notificationRepository.addNotifications(notifications);
    }
    
    
   
    
}

          