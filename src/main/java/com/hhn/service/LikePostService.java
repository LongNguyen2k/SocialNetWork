/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service;

import com.hhn.pojos.LikePost;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;

/**
 *
 * @author Windows 10
 */
public interface LikePostService {
    boolean checkPostUserLike(String userNameLoggedIn , String postIdLike);
}
