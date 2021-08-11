/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Windows 10
 */
@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    
    @RequestMapping("/profile")
    public String profilePage(Model model){
        model.addAttribute("userProfile", this.userService.getUserProfile());
        return "profilePage";
    }
}
