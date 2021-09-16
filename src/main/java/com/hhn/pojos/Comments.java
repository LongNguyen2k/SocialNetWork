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
import javax.validation.constraints.Size;

/**
 *
 * @author Windows 10
 */
@Entity
@Table(name="comments")
public class Comments implements Serializable{

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String comment;
    
    @Column(name = "posted_at")
    private Date postAt;
    
    private int likes; 
    
    @JoinColumn(name = "user_id" )
    @ManyToOne()
    private User user;
    
    @JoinColumn(name = "post_id")
    @ManyToOne()
    private Post post;
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPostAt() {
        return postAt;
    }

    public void setPostAt(Date postAt) {
        this.postAt = postAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}