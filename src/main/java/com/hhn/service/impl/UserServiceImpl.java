/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hhn.pojos.User;
import com.hhn.repository.UserRepository;
import com.hhn.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService{
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
   
    @Override
    public List<User> getUserProfile(String userName) {
       return this.userRepository.getUserProfile(userName);
    }

    @Override
    @Transactional
    public boolean addUser(User user) {
        
         user.setPassword(passwordEncoder.encode(user.getPassword()));  
         user.setuRole(User.USER);
         try {
            // lưu file trên cloud và link ảnh trong db
               Map r = this.cloudinary.uploader().upload(user.getFile().getBytes(),
                       ObjectUtils.asMap("resource_type","auto"));
               
               user.setAvatar((String) r.get("secure_url"));
               
                return this.userRepository.addUser(user);
           } catch (IOException ex) {
               System.out.println("Add User" + ex.getMessage());
           }
        return false;
    }
    
     @Override
    public boolean updateUser(User user) {
        
        
        if(user.getFile().getSize() == 0 )
        {
             return this.userRepository.updateProfile(user);
        }
        else
        {
        
        
       try {
            // lưu file trên cloud và link ảnh trong db
               Map r = this.cloudinary.uploader().upload(user.getFile().getBytes(),
                       ObjectUtils.asMap("resource_type","auto"));
               
               user.setAvatar((String) r.get("secure_url"));
               
                return this.userRepository.updateProfile(user);
           } catch (IOException ex) {
               System.out.println("Update User" + ex.getMessage());
           }
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       List<User> users = this.getUsers(username);
       if(users.isEmpty())
           throw new UsernameNotFoundException("USER does not exists"); 
       User user = users.get(0);
        Set<GrantedAuthority> auth = new HashSet<>();
       auth.add(new SimpleGrantedAuthority(user.getuRole()));
       return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auth);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers(String username) {
        return userRepository.getUsers(username);
    }

    @Override
    public boolean checkUserName(String username) {
       List<User> users = userRepository.checkUserName(username);
       if(!users.isEmpty())
           return true;
       return false;
    }

    @Override
    public List<User> getUserIdLoggedIn(String userId) {
       return this.userRepository.getUserIdLoggedIn(userId);
    }
    
    

    @Override
    public User getUserID(String id) {
        return this.userRepository.getUserID(id);
    }

    @Override
    public List<User> getListAdminRole() {
       return this.userRepository.getUserAdminRole();
    }

    @Override
    public List<User> getCurrentLoggedInUser(String username) {
        return this.userRepository.getCurrentLoggedInUser(username);
    }

    @Override
    public List<Object[]> getUserLikeMost(String username) {
        return this.userRepository.getUserLikeMost(username);
    }

    @Override
    public List<Object[]> getUserCommentMost(String username) {
        return this.userRepository.getUserCommentMost(username);
    }

   

  
    
    
  
    
}
