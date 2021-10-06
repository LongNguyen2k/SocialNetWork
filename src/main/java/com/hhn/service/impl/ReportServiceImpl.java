/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.hhn.pojos.Comments;
import com.hhn.pojos.Post;
import com.hhn.pojos.ReportComment;
import com.hhn.pojos.ReportPost;
import com.hhn.repository.ReportRepository;
import com.hhn.service.ReportService;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService{

    @Autowired
    private ReportRepository reportRepository;
    
    @Override
    public boolean addReport(ReportPost reportPost) {
        Calendar cal = Calendar.getInstance();
        reportPost.setCheckVerified(false);
        reportPost.setSendReportAt(new Timestamp(cal.getTimeInMillis()));
        return this.reportRepository.addReportPost(reportPost);
    }

    @Override
    public boolean addReportComment(ReportComment reportComment) {
        Calendar cal = Calendar.getInstance();
        reportComment.setCheckVerified(false);
        reportComment.setSendReportAt(new Timestamp(cal.getTimeInMillis()));
        return this.reportRepository.addReportComment(reportComment);
    }

    @Override
    public List<ReportPost> getListReportPost() {
        return this.reportRepository.listReporPostt();
    }

    @Override
    public ReportPost getReportPostID(int reportPostID) {
       return this.reportRepository.getReportPostID(reportPostID);
    }
    
    @Override
    public ReportComment getReportCommentID(int reportCommentID) {
        return this.reportRepository.getReportCommentID(reportCommentID);
    }

    @Override
    public boolean removePost(ReportPost reportPostObj , Post postSelected) {
        reportPostObj.setCheckVerified(true);
        postSelected.setCheckReported(true);
        return this.reportRepository.removePost(reportPostObj, postSelected);
    }
    
     @Override
    public boolean removeComment(ReportComment reportCommentObj , Comments commentSelected) {
        reportCommentObj.setCheckVerified(true);
        commentSelected.setCheckReported(true);
        return this.reportRepository.removeComment(reportCommentObj, commentSelected);
    }

    @Override
    public boolean deniedRemovePost(ReportPost reportPostObj) {
        reportPostObj.setCheckVerified(true);
        return this.reportRepository.deniedRemovePost(reportPostObj);
    }

    @Override
    public List<ReportComment> getListReportComments() {
       return this.reportRepository.listReportComments();
    }


    @Override
    public boolean deniedRemoveComment(ReportComment reportCommentObj) {
       reportCommentObj.setCheckVerified(true);
       return this.reportRepository.deniedRemoveComment(reportCommentObj);
    }

    
    
}
