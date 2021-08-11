/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.hhn.pojos.User;
import com.hhn.repository.UserRepository;
import com.hhn.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows 10
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository UserRepository;
    @Override
    public List<User> getUserProfile() {
       return this.UserRepository.getUserProfile();
    }
    
}
