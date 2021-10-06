/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository;

import com.hhn.pojos.Comments;
import com.hhn.pojos.Post;
import com.hhn.pojos.ReportComment;
import com.hhn.pojos.ReportPost;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface ReportRepository {
    boolean addReportPost(ReportPost reportPost);
    boolean addReportComment(ReportComment reportComment);
    List<ReportPost> listReporPostt();
    List<ReportComment> listReportComments();
    ReportPost getReportPostID(int reportID);
    ReportComment getReportCommentID(int reportID);
    boolean removePost(ReportPost reportedPost , Post postSelected);
    boolean removeComment(ReportComment repotedComment , Comments commentSelected);
    boolean deniedRemovePost(ReportPost reportedPost);
    boolean deniedRemoveComment(ReportComment reportedComment);
}
