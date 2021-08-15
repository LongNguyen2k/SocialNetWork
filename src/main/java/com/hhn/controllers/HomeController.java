/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.service.CategoryPostService;
import com.hhn.service.PostService;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @ModelAttribute
    public void commonAttribute(Model model){
    model.addAttribute("categories" , this.CategoryPostService.getCategories());
 
//    ,@PathVariable("categoryId")int CategoryId
    }
    
    @RequestMapping("/")
    public String index(Model model , @RequestParam(value ="kw" , required = false , defaultValue = "") String kw  ){
       
        model.addAttribute("postNewFeed" , this.postService.getPostNewFeed(kw));

        return "homePage";
    }
    @RequestMapping(value ="/postByCategoryPost")
    public String getPostByCateId(Model model ,@RequestParam(value ="kw" , required = false , defaultValue = "") String kw  ,
            @RequestParam(value="catPostId", required = false , defaultValue = "") String id){
        model.addAttribute("listPostFromCategory" , this.postService.getPostFromCategoryPost(kw,id));
        return"postByCategoryPost";
    }
  
}
