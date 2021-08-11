/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.pojos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.criteria.Fetch;

/**
 *
 * @author Windows 10
 */
@Entity
@Table(name = "post")
public class Post implements Serializable{

  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    
    @Column(name = "posted_at")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date postAt;
    
    @Column(name="address_post")
    private String addressPost;
    
    private int likes;
    private String image;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" )
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryPost categoryPost;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public void setAddressPost(String addressPost) {
        this.addressPost = addressPost;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
      public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CategoryPost getCategoryPost() {
        return categoryPost;
    }

    public void setCategoryPost(CategoryPost categoryPost) {
        this.categoryPost = categoryPost;
    }

    public Date getPostAt() {
        return postAt;
    }

    public void setPostAt(Date postAt) {
        this.postAt = postAt;
    }
    
    
}
