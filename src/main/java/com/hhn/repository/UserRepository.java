 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository;

import com.hhn.pojos.User;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface UserRepository {
    List<User> getUserProfile(String userName);
    boolean addUser(User user);
    List<User> getUsers(String username);
    List<User> checkUserName(String username);
    List<User> getUserIdLoggedIn(String UserId);
    List<User> getCurrentLoggedInUser(String username);
    User getUserID(String id);
    List<User> getUserAdminRole();
}
