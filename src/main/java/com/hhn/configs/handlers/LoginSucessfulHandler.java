/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.configs.handlers;

import com.hhn.pojos.User;
import com.hhn.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 *
 * @author Windows 10
 */

public class LoginSucessfulHandler implements AuthenticationSuccessHandler{
    // gọi userserivce 
    @Autowired
    private UserService userDetailsService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse reqResponse, Authentication a) throws IOException, ServletException {
        // phương thức sẽ được gọi sau khi đăng nhập thành công  // a là user đăng nhập vào
        User currentLoginUser = this.userDetailsService.getUsers(a.getName()).get(0);
        request.getSession().setAttribute("currentLoginUser", currentLoginUser);
        // dữ liệu user được lưu vào session
        reqResponse.sendRedirect("/SocialNetWork/user/");
    }
    
}
