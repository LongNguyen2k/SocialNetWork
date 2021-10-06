/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service;

import com.hhn.pojos.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Windows 10
 */
public interface UserService extends UserDetailsService{
    List<User> getUserProfile(String userName);
    boolean addUser(User user);
    List<User> getUsers(String username);
    boolean checkUserName(String username);
    List<User> getUserIdLoggedIn(String UserId);
     User getUserID(String id);
     List<User> getListAdminRole();
    List<User> getCurrentLoggedInUser(String username);
}
