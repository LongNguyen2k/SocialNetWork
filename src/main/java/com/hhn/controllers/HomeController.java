/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.service.CategoryPostService;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Windows 10
 */
@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private CategoryPostService CategoryPostService;
    
    @ModelAttribute
    public void commonAttribute(Model model){
    model.addAttribute("categories" , this.CategoryPostService.getCategories());
    }
    
    @RequestMapping("/")
    public String index(Model model){
       
        return "homePage";
    }
}
