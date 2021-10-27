/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.validator;

import com.hhn.pojos.User;
import com.hhn.repository.UserRepository;
import com.hhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Windows 10
 */
@Component
public class UserValidator implements Validator{
    
   
    
     @Override
    public boolean supports(Class<?> clazz) {
       return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User u = (User) target;
      if (!u.getPassword().trim().equals(u.getConFirmPassWord().trim()))
            errors.rejectValue("password","user.password.error.notMatchMsg");
      if(u.getAvatar() == null)
      {
       if(u.getFile().getSize() == 0)
       {
           errors.rejectValue("file", "user.file.error.noFileChoose");
       }
      }
       if(u.getUsername().contains(" "))
       {
           errors.rejectValue("username", "user.username.spaceError");
       }
       if(u.getBirthday() == null)
       {
           errors.rejectValue("birthday" , "user.birthday.DateError");
       }
             
    } 
}
