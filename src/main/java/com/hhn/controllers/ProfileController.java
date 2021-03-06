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
import com.hhn.service.AuctionService;
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

     
    @Autowired
    private AuctionService auctionService;
   
    @RequestMapping(value = "/user/profile/{username}")
    public String profilePage(Model model , @RequestParam(value ="kw" , required = false , defaultValue = "") String kw , 
           @PathVariable(value = "username")String userName , HttpServletRequest request){
        model.addAttribute("userProfile", this.userService.getUserProfile(userName));
        model.addAttribute("postUserProfile" , this.postService.getPostsUserProfile(kw , userName ));
        model.addAttribute("likePost", new LikePost());
        model.addAttribute("likeComment", new LikeComment());
        return "profilePage";
    }
  

    @RequestMapping(value="/user/addPostPage")
    public String getUserIdPost(Model model  ,  @RequestParam(required = false)Map<String,String> params )
    {    
        String userID = params.get("userId");
        model.addAttribute("post", new Post());    
        model.addAttribute("userIdLoggedIn" , this.userService.getUserIdLoggedIn(userID).get(0));
        return "addPostPage";
    }
    
      @RequestMapping("/user/editProfileUser")
    public String editProfilePage(Model model, HttpServletRequest request)
    {
       
         model.addAttribute("user", this.userService.getUserID(request.getUserPrincipal().getName()));
         
        return "editProfilePage";
    }
    @PostMapping("/user/editProfilePost")
    public String editProfilePost(Model model ,@ModelAttribute(value = "user")User user ,
           HttpServletRequest request)
    {
          model.addAttribute("user", this.userService.getUserID(request.getUserPrincipal().getName()));
       
       
            if(this.userService.updateUser(user))
               return String.format("redirect:/user/profile/%s",request.getUserPrincipal().getName());
            else
               model.addAttribute("errMsG" , "Da Co Loi Xay Ra !");
            return "editProfilePage";
            
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
    @PostMapping(value ="/user/updatePost/")
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
    
    @RequestMapping("/user/delete/{postId}/{username}")
    public String deletePost(Model model , @PathVariable("postId") String postId, @PathVariable(value = "username")String userName)
    {
        // x??a 1 b??i vi???t th?? ph???i x??a h???t to??n b??? nh???ng ph???n t??? li??n quan trong b???ng tr?????c khi x??a 
        // n???u b??i vi???t ??ang c?? ?????u gi?? th?? ko th??? x??a ???????c v?? xu???t th??ng b??o l???i 
        // n???u nh?? b??i vi???t kh??ng c?? ?????u gi?? th?? m???i b???t ?????u vi???c x??a
        // x??a likepost v?? like comment c???a b??i vi???t ????  
        // x??a reportpost v?? reportcomment 
        // x??a comment
        // x??a post
   
       Post currentSelectedPost = this.postService.getPostId(postId);
       
       if(this.auctionService.checkHaveAuction(currentSelectedPost) == false)
       {
           
            if(this.postService.deleteCpRelatedToPost(currentSelectedPost) == true)
            {
                if(this.postService.deletePost(currentSelectedPost))
                    return String.format("redirect:/user/profile/%s",userName);
                else
                {
                    model.addAttribute("errMsG", "Da Co Loi Xay Ra");
                    return "redirect:/user/"; 
                }
            }
            else
            {
                 model.addAttribute("errMsG", "Da Co Loi Xay Ra");
                 return "redirect:/user/"; 
            }
       }
       else
       {
           model.addAttribute("haveCurrentAuction", "B??i vi???t n??y ??ang ???????c t??? ch???c ?????u gi??. B???n kh??ng th??? x??a n?? l??c n??y !");
           return String.format("forward:/user/profile/%s",userName);
       }
    }
    
    
    
    
    
}
