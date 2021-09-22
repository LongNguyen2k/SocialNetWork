/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.pojos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Windows 10
 */
@Entity
@Table(name = "auctions")
public class Auctions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @ManyToOne
    @JoinColumn(name = "bidding_user")
    private User biddingUser;
    @ManyToOne
    @JoinColumn(name ="bidding_post")
    private Post biddingPost;
    private BigDecimal biddingPrice;
    private boolean biddingStatus;
    private Timestamp biddingAt;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public BigDecimal getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(BigDecimal biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public boolean isBiddingStatus() {
        return biddingStatus;
    }

    public void setBiddingStatus(boolean biddingStatus) {
        this.biddingStatus = biddingStatus;
    }

    public Timestamp getBiddingAt() {
        return biddingAt;
    }

    public void setBiddingAt(Timestamp biddingAt) {
        this.biddingAt = biddingAt;
    }

    public User getBiddingUser() {
        return biddingUser;
    }

    public void setBiddingUser(User biddingUser) {
        this.biddingUser = biddingUser;
    }

    public Post getBiddingPost() {
        return biddingPost;
    }

    public void setBiddingPost(Post biddingPost) {
        this.biddingPost = biddingPost;
    }
   
    
}
