/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service;

import com.hhn.pojos.Comments;
import com.hhn.pojos.Post;
import com.hhn.pojos.ReportComment;
import com.hhn.pojos.ReportPost;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface ReportService {
    boolean addReport(ReportPost reportPost);
    boolean addReportComment(ReportComment reportComment);
    List<ReportPost> getListReportPost();
    List<ReportComment> getListReportComments();
    ReportPost getReportPostID(int reportPost);
    ReportComment getReportCommentID(int reportComment);
    boolean removePost(ReportPost reportPostObj , Post postSelected);
    boolean removeComment(ReportComment reportCommentObj , Comments commentSelected);
    boolean deniedRemovePost(ReportPost reportPostObj);
    boolean deniedRemoveComment(ReportComment reportCommentObj);
    
}
