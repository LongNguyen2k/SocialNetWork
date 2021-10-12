/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.pojos.Auctions;
import com.hhn.pojos.Comments;
import com.hhn.pojos.LikeComment;
import com.hhn.pojos.LikePost;
import com.hhn.pojos.Notifications;
import com.hhn.service.AuctionService;
import com.hhn.service.CategoryPostService;
import com.hhn.service.CommentsService;
import com.hhn.service.LikeCommentService;
import com.hhn.service.LikePostService;
import com.hhn.service.NotificationService;
import com.hhn.service.PostService;
import com.hhn.service.UserService;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
    @Autowired
    private AuctionService auctionService;

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
    @RequestMapping(value ="/user/postByCategoryPost")
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
       int notficationType = 1 ;
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
                    notifications.setType(notficationType);
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
       int  notficationType = 3 ;
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
                    notifications.setType(notficationType);
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
        int  notficationType = 2 ;
        String  userLoggedInName = params.get("username");
        String commentPostId = params.get("post_id");
        String commentText = params.get("commentText");
        if(!(commentText.length() < 2  || commentText.isEmpty() || commentText.isBlank()))
        {
            if(this.commentsService.addComments(userLoggedInName, commentPostId , commentText, comments) == true)
            { 
                notifications.setType(notficationType);
                this.notificationService.addNotifications(userLoggedInName, commentPostId, notifications);
                return String.format("redirect:/user/comment/%s",String.format("?username=%s&post_id=%s",userLoggedInName,commentPostId));
            }
            else 
                model.addAttribute("errMsG" , "SomethingWrong !");
        }
             model.addAttribute("errMsG" , "Noi Dung Comment Qua Ngan !");
         return String.format("forward:/user/comment/%s",String.format("?username=%s&post_id=%s",userLoggedInName,commentPostId));
    }
    
    @RequestMapping(value = "/user/notification/")
    public String NotificationPage(Model model)
    {
        return "notfications";
    }
    @RequestMapping("/user/auctionpage/")
    public String auctionPage(Model model , @RequestParam(required = false)Map<String,String> params )
    {
        String post_id = params.get("post_id");
        model.addAttribute("auctionBidding",new Auctions());
        model.addAttribute("postBiddingInfo",this.auctionService.getBiddingInfoPost(post_id));
        model.addAttribute("biddingPriceList", this.auctionService.getListOfBiddingFromPost(post_id));
        model.addAttribute("currentMaxBiddPrice", this.auctionService.getMaxBiddingPrice(post_id));
        model.addAttribute("notifications", new Notifications());
        return "auctions";
    }
    @GetMapping(value = "/user/addBidding/")
    public String addBiddingForPost(Model model, @ModelAttribute(value = "auctionBidding")Auctions auctions 
            ,@RequestParam(required = false)Map<String,String> params , @ModelAttribute(value = "notifications")Notifications notifications )
    {
        BigDecimal priceBidding = new BigDecimal(params.get("biddingPrice")) ;
        String username = params.get("username");
        String POST_ID = params.get("post_id");
       int notficationType = 4 ;
        if(this.auctionService.findWinner(POST_ID) == false)
        {
            if(auctionService.checkingStartPrice(priceBidding, POST_ID) == true)
            {

                if(auctionService.checkBiddingPricePost(priceBidding, POST_ID) == true)
                {

                    if(this.auctionService.addnewBiddingForPost(username, POST_ID, priceBidding, auctions))
                    {
                        // thêm notification // sender là người ra giá receiver là người nhận đấu giá 
                        notifications.setType(notficationType);
                        this.notificationService.addNotifications(username,POST_ID,notifications);  
                         model.addAttribute("BiddingPriceAccept", "Bạn đã đấu giá thành công  ! ");
                          return String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=%s",username,POST_ID));
                    }
                    else
                    {
                        model.addAttribute("BiddingPriceAccept", "Đã có lỗi xảy trong server ! ");
                         return String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=%s",username,POST_ID));
                    }
                }
                else
                    
                    model.addAttribute("BiddingPriceTooSmall","Giá trị đấu giá của bạn nhỏ hơn so với người ra giá trước đó !");

            }
            else 
            {

                 model.addAttribute("errorBiddingPrice", "Giá quá nhỏ so với giá khởi đầu của món hàng !");
            }
        }
        else
        {
            model.addAttribute("AuctionExpire", "Cuộc đấu giá đã tìm được người chiến thắng,cảm ơn bạn đã ghé thăm !!! "); ;
        }

        return String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=%s",username,POST_ID));
    }
    
    @GetMapping(value = "/user/chooseWinnerBid/")
    public String chooseWinner(Model model , @RequestParam(required = false)Map<String,String> params , 
            @ModelAttribute(value = "notifications")Notifications notifications)
    {
        String username = params.get("username");
        String POST_ID = params.get("post_id");
        BigDecimal priceBidding = new BigDecimal(params.get("biddingPrice")) ;
        Timestamp biddingAt = Timestamp.valueOf(params.get("biddingAt"));
        int notficationType = 5;
        if( this.auctionService.findWinner(POST_ID) == false)
        {
            if(this.auctionService.chooseWinner(username, POST_ID, priceBidding, biddingAt))
            {
                notifications.setType(notficationType);
                // username là người nhận // postID là người gửi 
                this.notificationService.addNotifications(username, POST_ID, notifications);
                model.addAttribute("ChooseWinnerPopup", "Bạn đã chọn được người chiến thắng đấu giá  ! ");  
               return String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=%s",username,POST_ID));
            }
            else
            {
                 model.addAttribute("ChooseWinnerPopup", "Have Something wrong in the server ! ");
                return String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=%s",username,POST_ID));
            }
        }
        else
        {
             model.addAttribute("AuctionExpire", "Bài viết này đã có người chiến thắng đấu giá, bạn không thể chọn thêm người chiến thắng được nữa ! ");  
             return String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=%s",username,POST_ID));
        }

    }
  
}

//String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=&s",username,POST_ID))