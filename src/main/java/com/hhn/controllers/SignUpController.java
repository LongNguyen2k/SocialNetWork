/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hhn.pojos.User;
import com.hhn.service.UserService;
import com.hhn.validator.UserValidator;
import com.hhn.validator.WebAppValidator;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Windows 10
 */
@Controller
public class SignUpController {
  
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private WebAppValidator userValidator;
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.setValidator(userValidator);
    }
    
    @RequestMapping("/signup")
    public String signUpPage(Model model){
        model.addAttribute("user" , new User());
        return "signUpPage";
    }
    @PostMapping("/signup")
    private String addUser( Model model , @ModelAttribute(value="user")@Valid User user ,
            BindingResult result){

        
        if(userDetailsService.checkUserName(user.getUsername()) == true)
          model.addAttribute("errUserNameDuplicate" ,  "UserName Đã Có Người Sử Dụng");
        
       if(!result.hasErrors()){
            if(this.userDetailsService.addUser(user) == true)
                return "redirect:/signin";
            else 
                model.addAttribute("errMsG" , "Đã có lỗi xảy ra !");
        }
        return "signUpPage";
    }
    
    
    
}
