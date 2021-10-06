/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.pojos.Comments;
import com.hhn.pojos.Notifications;
import com.hhn.pojos.Post;
import com.hhn.pojos.ReportComment;
import com.hhn.pojos.ReportPost;
import com.hhn.pojos.User;
import com.hhn.service.CommentsService;
import com.hhn.service.NotificationService;
import com.hhn.service.PostService;
import com.hhn.service.ReportService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Windows 10
 */
@Controller
public class AdminController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentsService commentService;
    @GetMapping("/admin/statistical")
    public String adminPage(Model model){
        return "adminPage";
    }
    @GetMapping("/admin/directionalPage")
    public String directionalAdminPage(Model model)
    {
        return "directionalAdminPage";
    }
    
    @GetMapping("/admin/reportListAdminPage")
    public String adminReportList(Model model)
    {   
        model.addAttribute("listReportPost",this.reportService.getListReportPost());
         model.addAttribute("notifications", new Notifications());
        return "adminReportListPage";
    }
    @GetMapping("/admin/removePost")
    public String removePost(Model model , @RequestParam(required = false)Map<String,String> params 
            ,@ModelAttribute(value = "notifications")Notifications notifications )
    {
        String postIDReport = params.get("postID");
        int reportObject = Integer.parseInt(params.get("reportID"));
        Post postReported = this.postService.getPostId(postIDReport);
        ReportPost reportPostSelect = this.reportService.getReportPostID(reportObject);
        User userSendReport = reportPostSelect.getUserSendReport();
        User userCreatePost = postReported.getUser();
        User adminApproveReport = reportPostSelect.getAdminReceiveReport();
        int notificationUserSendeReport = 6 ;
        int notificationUserCreatePost = 7 ;
        // thêm notification cho người gửi báo cáo và người đăng bài viết
        if(this.reportService.removePost(reportPostSelect, postReported) == true)
        {
            notifications.setType(notificationUserSendeReport);
            // gửi thông báo cho người gửi báo cáo 
            this.notificationService.addNotificationAdmin(adminApproveReport,userSendReport,notifications);
            // gửi thông báo cho người đăng bài viết 
            notifications.setType(notificationUserCreatePost);
            this.notificationService.addNotificationAdmin(adminApproveReport, userCreatePost, notifications);
            return "redirect:/admin/reportListAdminPage";
        }
        model.addAttribute("errorMsg","Something wrong while update Database!");
        return "adminReportListPage";
    }
    
    @GetMapping("/admin/deniedRemovePost")
    public String deniedReportPost(Model model , @RequestParam(required = false)Map<String,String> params , 
            @ModelAttribute(value = "notifications")Notifications notifications)
    {
        String postIDReport = params.get("postID");
        int reportObject = Integer.parseInt(params.get("reportID"));
        ReportPost reportPostSelect = this.reportService.getReportPostID(reportObject);
        User userSendReport = reportPostSelect.getUserSendReport();
        User adminApproveReport = reportPostSelect.getAdminReceiveReport();
        int notificationDeniedReport = 8 ; 
        // thêm notification cho người gửi báo cáo 
        if(this.reportService.deniedRemovePost(reportPostSelect) == true)
        {
            notifications.setType(notificationDeniedReport);
            this.notificationService.addNotificationAdmin(adminApproveReport, userSendReport, notifications);
            return "redirect:/admin/reportListAdminPage";
        }
        model.addAttribute("errorMsg","Something wrong while update Database!");
        return "adminReportListPage";
    }
    
    @GetMapping("/admin/reportListCommentPage")
    public String adminCommentReportList(Model model)
    {
         model.addAttribute("listReportComment",this.reportService.getListReportComments());
        model.addAttribute("notifications", new Notifications());
        return "adminReportListCommentPage";
    }
    @GetMapping("/admin/removeComment")
    public String removeComment(Model model , @RequestParam(required = false)Map<String,String> params 
            ,@ModelAttribute(value = "notifications")Notifications notifications )
    {
    
        String commentIDReport = params.get("comment_ID");
        int reportObject = Integer.parseInt(params.get("reportID"));
        Comments commentReported = this.commentService.getCommentsID(commentIDReport);
        ReportComment reportCommentSelect = this.reportService.getReportCommentID(reportObject);
        User userSendReport = reportCommentSelect.getUserSendReport();
        User userCreateComment = commentReported.getUser();
        User adminApprovedReport = reportCommentSelect.getAdminReceiveReport();
         int notificationUserSendeReport = 9 ;
        int notificationUserCreateComment = 10 ;
        if(this.reportService.removeComment(reportCommentSelect, commentReported) == true)
        {
            notifications.setType(notificationUserSendeReport);
            this.notificationService.addNotificationAdmin(adminApprovedReport, userSendReport, notifications);
            notifications.setType(notificationUserCreateComment);
            this.notificationService.addNotificationAdmin(adminApprovedReport, userCreateComment, notifications);
            return "redirect:/admin/reportListCommentPage";
        }   
        model.addAttribute("errorMsg","Something wrong while update Database!");
        return "adminReportListCommentPage";
    }
    @GetMapping("/admin/deniedRemoveComment")
    public String deniedRemoveComment(Model model , @RequestParam(required = false)Map<String,String> params , 
            @ModelAttribute(value = "notifications")Notifications notifications)
    {
        String commentIDReport = params.get("comment_ID");
        int reportObject = Integer.parseInt(params.get("reportID"));
        ReportComment reportCommentSelect = this.reportService.getReportCommentID(reportObject);
        User userSendReport = reportCommentSelect.getUserSendReport();
        User adminApproveReport = reportCommentSelect.getAdminReceiveReport();
        int notificationDeniedReport = 11 ; 
        // thêm notification cho người gửi báo cáo 
        if(this.reportService.deniedRemoveComment(reportCommentSelect) == true)
        {
            notifications.setType(notificationDeniedReport);
            this.notificationService.addNotificationAdmin(adminApproveReport, userSendReport, notifications);
             return "redirect:/admin/reportListCommentPage";
        }
        model.addAttribute("errorMsg","Something wrong while update Database!");
        return "adminReportListCommentPage";
    }
    
   
}
