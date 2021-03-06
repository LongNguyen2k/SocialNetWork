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
    private int counterMaxPage = 30;
    private int counterCategoryPage = 10;
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
    

    @RequestMapping("/user")
    public String index(Model model , @RequestParam(value ="kw" , required = false , defaultValue = "") String kw  ,
            @RequestParam(required=false,defaultValue = "")Map<String,String> params , HttpServletRequest request ){
        int page = Integer.parseInt(params.getOrDefault("page","1"));
        String catPostID = params.get("catPostId");
        model.addAttribute("postNewFeed" , this.postService.getPostNewFeed(kw,page,catPostID));
        model.addAttribute("counter" , this.postService.countPost());
        model.addAttribute("counterMaxPage",counterMaxPage);
        model.addAttribute("likePost", new LikePost());
        model.addAttribute("likeComment", new LikeComment());
        model.addAttribute("notifications", new Notifications());
        model.addAttribute("userInfo", this.UserService.getUserProfile(request.getUserPrincipal().getName()));
        model.addAttribute("userLikeMost", this.UserService.getUserLikeMost(request.getUserPrincipal().getName()));
        model.addAttribute("userCommentMost", this.UserService.getUserCommentMost(request.getUserPrincipal().getName()));
        model.addAttribute("listPostMostInteract", this.postService.getPostInteractMost());
        model.addAttribute("postHaveHotAuctions", this.postService.getPostMostAuctionsRate());
        return "homePage";
    }
   
    @GetMapping("/user/likesPost")
    public String addLikesOrUnLike(Model model  ,@ModelAttribute(value="likePost")LikePost likePost ,
            @ModelAttribute(value="notifications")Notifications notifications , 
            @RequestParam(required = false)Map<String,String> params )
    {
       int notficationType = 1 ;
        String  userLoggedInName = params.get("username");
        String likedPostId = params.get("post_id");
        // t???n t???i user ???? like b??i vi???t 
        if( likePostService.checkPostUserLike(userLoggedInName, likedPostId) == true ){
             if(this.postService.unLikePost(userLoggedInName,likedPostId) == true)
                    return "redirect:/user/";
        }
        else{
            // user ch??a like b??i vi???t 
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
        
        // t???n t???i user ???? like comment 
         if( likeCommentService.checkCommentUserLike(userLoggedInName, likeCommentId) == true  ){
             if(this.commentsService.unLikeComment(userLoggedInName,likeCommentId) == true)
                    return String.format("redirect:/user/comment/%s",likedPostId);
        }
        else{
            // user ch??a like comment 
                if(this.commentsService.likeComment(userLoggedInName,likeCommentId ,likeComment) == true)
                {
                    notifications.setType(notficationType);
                    this.notificationService.addNotifications(userLoggedInName, likeCommentId, notifications);
                    return String.format("redirect:/user/comment/%s",likedPostId);
                }
        }
        return String.format("forward:/user/comment/%s",String.format("?username=%s&post_id=%s",userLoggedInName,likedPostId));
    }
    
    @RequestMapping(value = "/user/comment/{postID}")
    public String commentPage(Model model ,@PathVariable(value = "postID")int postID ,  HttpServletRequest request)
    {
        String userCurrentLogged =  request.getUserPrincipal().getName();
        model.addAttribute("postDetail", this.postService.getPostDetail(postID));
        model.addAttribute("userCurrentComment", this.UserService.getUsers(userCurrentLogged));
        model.addAttribute("commentOfPost", this.commentsService.getCommentFromPost(postID));
        model.addAttribute("countComment", this.commentsService.countCommentList(postID));
         model.addAttribute("post_id", postID);
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
    
    @RequestMapping(value = "/user/notification")
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
                        // th??m notification // sender l?? ng?????i ra gi?? receiver l?? ng?????i nh???n ?????u gi?? 
                        notifications.setType(notficationType);
                        this.notificationService.addNotifications(username,POST_ID,notifications);  
                         model.addAttribute("BiddingPriceAccept", "B???n ???? ?????u gi?? th??nh c??ng  ! ");
                          return String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=%s",username,POST_ID));
                    }
                    else
                    {
                        model.addAttribute("BiddingPriceAccept", "???? c?? l???i x???y trong server ! ");
                         return String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=%s",username,POST_ID));
                    }
                }
                else
                    
                    model.addAttribute("BiddingPriceTooSmall","Gi?? tr??? ?????u gi?? c???a b???n nh??? h??n so v???i ng?????i ra gi?? tr?????c ???? !");

            }
            else 
            {

                 model.addAttribute("errorBiddingPrice", "Gi?? qu?? nh??? so v???i gi?? kh???i ?????u c???a m??n h??ng !");
            }
        }
        else
        {
            model.addAttribute("AuctionExpire", "Cu???c ?????u gi?? ???? t??m ???????c ng?????i chi???n th???ng,c???m ??n b???n ???? gh?? th??m !!! "); ;
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
                // username l?? ng?????i nh???n // postID l?? ng?????i g???i 
                this.notificationService.addNotifications(username, POST_ID, notifications);
                model.addAttribute("ChooseWinnerPopup", "B???n ???? ch???n ???????c ng?????i chi???n th???ng ?????u gi??  ! ");  
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
             model.addAttribute("AuctionExpire", "B??i vi???t n??y ???? c?? ng?????i chi???n th???ng ?????u gi??, b???n kh??ng th??? ch???n th??m ng?????i chi???n th???ng ???????c n???a ! ");  
             return String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=%s",username,POST_ID));
        }

    }
  
}

//String.format("forward:/user/auctionpage/%s",String.format("?username=%s&post_id=&s",username,POST_ID))