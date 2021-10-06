/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "reportpost")
public class ReportPost implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_sender")
    private User userSendReport;
    
    @ManyToOne
    @JoinColumn(name = "user_receiver")
    private User adminReceiveReport;
    @ManyToOne
    @JoinColumn(name = "post_report")
    private Post postReport;
    
    @Size(min = 10 ,max = 10000,message = "{report.reason.errorLength}")
    @Column(name = "reasonreport")
    private String reasonReport;
    
    @Column(name = "check_verified")
    private boolean checkVerified;
    
    @Column(name = "sendreport_at")
    private Timestamp sendReportAt;
    
    
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserSendReport() {
        return userSendReport;
    }

    public void setUserSendReport(User userSendReport) {
        this.userSendReport = userSendReport;
    }

    public User getAdminReceiveReport() {
        return adminReceiveReport;
    }

    public void setAdminReceiveReport(User adminReceiveReport) {
        this.adminReceiveReport = adminReceiveReport;
    }

    public String getReasonReport() {
        return reasonReport;
    }

    public void setReasonReport(String reasonReport) {
        this.reasonReport = reasonReport;
    }

    public boolean isCheckVerified() {
        return checkVerified;
    }

    public void setCheckVerified(boolean checkVerified) {
        this.checkVerified = checkVerified;
    }
     public Post getPostReport() {
        return postReport;
    }

    public void setPostReport(Post postReport) {
        this.postReport = postReport;
    }

    public Timestamp getSendReportAt() {
        return sendReportAt;
    }

    public void setSendReportAt(Timestamp sendReportAt) {
        this.sendReportAt = sendReportAt;
    }
}
