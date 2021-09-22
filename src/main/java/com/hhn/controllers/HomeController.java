/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.pojos.Comments;
import com.hhn.pojos.LikeComment;
import com.hhn.pojos.LikePost;
import com.hhn.pojos.Notifications;
import com.hhn.service.CategoryPostService;
import com.hhn.service.CommentsService;
import com.hhn.service.LikeCommentService;
import com.hhn.service.LikePostService;
import com.hhn.service.NotificationService;
import com.hhn.service.PostService;
import com.hhn.service.UserService;
import java.util.Map;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Windows 10
 */
@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private CategoryPostService CategoryPostService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService UserService;
    @Autowired
    private LikePostService likePostService;
    @Autowired
    private LikeCommentService likeCommentService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private NotificationService notificationService;
    @ModelAttribute
    public void commonAttribute(Model model){
    model.addAttribute("categories" , this.CategoryPostService.getCategories());
    
//    ,@PathVariable("categoryId")int CategoryId
    }
    
    @ModelAttribute
    public void getNotificationsList(Model model , HttpServletRequest request )
    {   
        if(request.getUserPrincipal() != null )
        {
         String userReceiveNotify = request.getUserPrincipal().getName() ;
          model.addAttribute("notificationList", this.notificationService.getNotificationFromUser(userReceiveNotify));
        }
    }
    

    @RequestMapping("/user/")
    public String index(Model model , @RequestParam(value ="kw" , required = false , defaultValue = "") String kw  ,
            @RequestParam(required=false,defaultValue = "")Map<String,String> params , HttpServletRequest request ){
        int page = Integer.parseInt(params.getOrDefault("page","1"));
        model.addAttribute("postNewFeed" , this.postService.getPostNewFeed(kw,page));
        model.addAttribute("counter" , this.postService.countPost());
        model.addAttribute("likePost", new LikePost());
        model.addAttribute("likeComment", new LikeComment());
        model.addAttribute("notifications", new Notifications());
        return "homePage";
    }
    @RequestMapping(value ="/admin/postByCategoryPost")
    public String getPostByCateId(Model model ,@RequestParam(value ="kw" , required = false , defaultValue = "") String kw  ,
            @RequestParam(value="catPostId", required = false , defaultValue = "") String id){
        model.addAttribute("listPostFromCategory" , this.postService.getPostFromCategoryPost(kw,id));
        return"postByCategoryPost";
    }
    @GetMapping("/user/likesPost")
    public String addLikesOrUnLike(Model model  ,@ModelAttribute(value="likePost")LikePost likePost ,
            @ModelAttribute(value="notifications")Notifications notifications , 
            @RequestParam(required = false)Map<String,String> params )
    {
       int typeNotificationLike = 1 ;
        String  userLoggedInName = params.get("username");
        String likedPostId = params.get("post_id");
        // tồn tại user đã like bài viết 
        if( likePostService.checkPostUserLike(userLoggedInName, likedPostId) == true ){
             if(this.postService.unLikePost(userLoggedInName,likedPostId) == true)
                    return "redirect:/user/";
        }
        else{
            // user chưa like bài viết 
                if(this.postService.likePost(userLoggedInName,likedPostId ,  likePost) == true)
                {
                    // insert notification 
                    notifications.setType(typeNotificationLike);
                    this.notificationService.addNotifications(userLoggedInName, likedPostId, notifications);
                    return "redirect:/user/";
                }
        }
        return "redirect:/user/";
    }
    @GetMapping("/user/likesComment")
    public String addLikeOrUnLikeComment(Model model , @ModelAttribute(value ="likeComment")LikeComment likeComment
            , @ModelAttribute(value="notifications")Notifications notifications 
            , @RequestParam(required = false)Map<String,String> params)
    {
        int typeNotificationLike = 3 ;
        String  userLoggedInName = params.get("username");
        String likeCommentId = params.get("comment_id");
        String likedPostId = params.get("post_id");
        // tồn tại user đã like comment 
         if( likeCommentService.checkCommentUserLike(userLoggedInName, likeCommentId) == true  ){
             if(this.commentsService.unLikeComment(userLoggedInName,likeCommentId) == true)
                    return String.format("redirect:/user/comment/%s",String.format("?username=%s&post_id=%s",userLoggedInName,likedPostId));
        }
        else{
            // user chưa like comment 
                if(this.commentsService.likeComment(userLoggedInName,likeCommentId ,likeComment) == true)
                {
                    notifications.setType(typeNotificationLike);
                    this.notificationService.addNotifications(userLoggedInName, likeCommentId, notifications);
                    return String.format("redirect:/user/comment/%s",String.format("?username=%s&post_id=%s",userLoggedInName,likedPostId));
                }
        }
        return String.format("forward:/user/comment/%s",String.format("?username=%s&post_id=%s",userLoggedInName,likedPostId));
    }
    
    @RequestMapping(value = "/user/comment")
    public String commentPage(Model model , @RequestParam(required = false)Map<String,String> params )
    {
        model.addAttribute("comments", new Comments());
        String  userLoggedInName = params.get("username");
        int postCurrentSelectedComment = Integer.parseInt(params.get("post_id"));
        model.addAttribute("userCurrentComment", this.UserService.getUsers(userLoggedInName));
        model.addAttribute("post_id", postCurrentSelectedComment);
        model.addAttribute("commentOfPost", this.commentsService.getCommentFromPost(postCurrentSelectedComment));
        model.addAttribute("countComment", this.commentsService.countCommentList(postCurrentSelectedComment));
        return "commentPage";
    }
    
    @GetMapping(value = "/user/addComment")
    public String addComment(Model model, 
            @ModelAttribute(value = "comments")Comments comments ,
            @ModelAttribute(value="notifications")Notifications notifications,
            @RequestParam(required = false ,defaultValue = "")Map<String,String> params )
    {
         int typeNotificationLike = 2 ;
        String  userLoggedInName = params.get("username");
        String commentPostId = params.get("post_id");
        String commentText = params.get("commentText");
        if(!(commentText.length() < 2  || commentText.isEmpty() || commentText.isBlank()))
        {
            if(this.commentsService.addComments(userLoggedInName, commentPostId , commentText, comments) == true)
            { 
                notifications.setType(typeNotificationLike);
                this.notificationService.addNotifications(userLoggedInName, commentPostId, notifications);
                return String.format("redirect:/user/comment/%s",String.format("?username=%s&post_id=%s",userLoggedInName,commentPostId));
            }
            else 
                model.addAttribute("errMsG" , "SomethingWrong !");
        }
             model.addAttribute("errMsG" , "Noi Dung Comment Qua Ngan !");
         return String.format("forward:/user/comment/%s",String.format("?username=%s&post_id=%s",userLoggedInName,commentPostId));
    }
    
    @RequestMapping("/user/notifcation/")
    public String NotificationPage(Model model)
    {
        return "notfications";
    }
  
}
