/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Windows 10
 */
@Entity
@Table(name ="user")
public class User implements Serializable{

    public static final String  ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ; 
    
    @Size(min = 1 , max = 50 , message = "{user.username.lenErr}")
    private String username;
    @NotEmpty(message = "user.password.notEmpty")
    private String password;
    
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$" , 
            message = "{user.email.error.invalidMsg}")
    private String email;
    
    @Size(min = 3 , max = 50 , message = "{user.name.lenErr}")
    private String name;
    
    private String avatar;
    
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    
    @Column(name = "phonenumber")
    @Size(min = 9 , max = 11 , message = "{user.phonenumber.lenError}")
    @NotEmpty(message = "{user.phonenumber.notEmpty}")
    private String phoneNumber;
    
    @Column(name = "u_role")
    private String uRole;
    
    @OneToMany(mappedBy = "user")
    private List<Post> posts;
    
    @Transient
    private MultipartFile file;
    @Transient
    @NotEmpty(message = "user.password.notEmpty")
    private String conFirmPassWord;
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    /**
     *
     * @return
     */
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

     public String getConFirmPassWord() {
        return conFirmPassWord;
    }

    public void setConFirmPassWord(String conFirmPassWord) {
        this.conFirmPassWord = conFirmPassWord;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

     public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
