/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.pojos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Windows 10
 */
@Entity
@Table(name = "notifications")
public class Notifications implements Serializable{

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private int type;
    
    // 1 user có thể nhận nhiều notifications
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_user")
    private  User receiverUser;
    
    // 1 user có thể gửi nhiều notifications
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_user")
    private User senderUser;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }
   
}
