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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Windows 10
 */
@Entity
@Table(name = "post")
public class Post implements Serializable{

    public static final int likeCount = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 10 , max =500 , message = "{post.content.errorMsg}")
    private String content;
    
    @Column(name = "posted_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date postAt;
    
    private int likes;
    private String image;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" )
    @NotNull(message = "{post.user.Error}")
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @NotNull(message = "{post.categoryPost.Error}")
    private CategoryPost categoryPost;
    
     @Transient
    private MultipartFile file;
     
    @OneToMany(mappedBy = "post")
    private List<Comments> comments; 
    
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
    
}
