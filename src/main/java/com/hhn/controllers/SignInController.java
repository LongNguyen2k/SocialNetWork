/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Windows 10
 */
@Controller
public class SignInController {
    @RequestMapping("/signin")
    public String signInPage(Model model){
        return "signInPage";
    }
}
