/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.service.PostService;
import com.hhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Windows 10
 */
@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    
    @RequestMapping("/profile")
    public String profilePage(Model model , @RequestParam(value ="kw" , required = false , defaultValue = "") String kw){
        model.addAttribute("userProfile", this.userService.getUserProfile());
        model.addAttribute("postUserProfile" , this.postService.getPostsUserProfile(kw));
        return "profilePage";
    }
}
