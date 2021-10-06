/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.formatter.UserFormatter;
import com.hhn.pojos.LikeComment;
import com.hhn.pojos.LikePost;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.service.NotificationService;
import com.hhn.service.PostService;
import com.hhn.service.UserService;
import com.hhn.validator.WebAppValidator;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Windows 10
 */
@Controller
public class ProfileController{
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private NotificationService notificationService;
    
    @RequestMapping(value = "/user/profile")
    public String profilePage(Model model , @RequestParam(value ="kw" , required = false , defaultValue = "") String kw , 
            @RequestParam(value="userName" , required = false , defaultValue = "") String userName , HttpServletRequest request){
        model.addAttribute("userProfile", this.userService.getUserProfile(userName));
        model.addAttribute("postUserProfile" , this.postService.getPostsUserProfile(kw , userName ));
        model.addAttribute("likePost", new LikePost());
        model.addAttribute("likeComment", new LikeComment());
        return "profilePage";
    }
    @GetMapping("/user/editProfilePage")
    public String editProfilePage(Model model ,@RequestParam(required = false)Map<String,String> params)
    {
        String userID = params.get("userID");
         model.addAttribute("userProfile", this.userService.getUserID(userID));
         
        return "editProfilePage";
    }
      @PostMapping("/user/editProfiles")
    public String editProfilePage(Model model ,@ModelAttribute(value = "userProfile")@Valid User user ,
            BindingResult result, @RequestParam(required = false)Map<String,String> params)
    {
         
        String username = params.get("username");
       
         if(!result.hasErrors())
         {
             model.addAttribute("errMsG" , "Da Co Loi Xay Ra !");
             return "redirect:/user/";
         }
         else
            return "editProfilePage";
    }

    @RequestMapping(value="/user/addPostPage")
    public String getUserIdPost(Model model  ,  @RequestParam(required = false)Map<String,String> params )
    {    
        String userID = params.get("userId");
        model.addAttribute("post", new Post());    
        model.addAttribute("userIdLoggedIn" , this.userService.getUserIdLoggedIn(userID).get(0));
        return "addPostPage";
    }
    
    
    @PostMapping(value = "/user/addPost")
    public String addNewPost(Model model ,@ModelAttribute(value = "post")@Valid Post post  ,BindingResult result ){
        if(!result.hasErrors())
        {   
            
            if(this.postService.addNewPost(post) == true)
                 return "redirect:/user/";
            else
                model.addAttribute("errMsG" , "Da Co Loi Xay Ra !");
                    
        }
        return "addPostPage";
    }
    @GetMapping("/user/updatePostPage")
    public String getIdPost(Model model , @RequestParam(value = "postId" , required = false ) String postId){
        model.addAttribute("postUpdate" , this.postService.getPostId(postId)  );
        return "UpdatePostPage";
    }
    @PostMapping("/user/updatePost")
    public String updatePost(Model model , @ModelAttribute(value = "postUpdate")@Valid Post post  ,BindingResult result)
    {  
       
        if(!result.hasErrors())
        {
            if(this.postService.updatePost(post) == true)
                return "redirect:/user/";
            else
                model.addAttribute("errMsG" , "Da Co Loi Xay Ra !");
        }
        return "UpdatePostPage";
    }
    
    @RequestMapping("/user/delete/{postId}")
    public String deletePost(Model model , @PathVariable("postId") String postId)
    {
       Post currentSelectedPost = this.postService.getPostId(postId);
       if(this.postService.deletePost(currentSelectedPost) == true)
           return "redirect:/user/";
       else
           model.addAttribute("errMsG", "Da Co Loi Xay Ra");
       return "redirect:/user/"; 
    }
    
    
    
    
    
}
