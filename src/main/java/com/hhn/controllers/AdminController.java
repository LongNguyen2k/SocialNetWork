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
import com.hhn.service.StatsService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private  StatsService statsService;
    @GetMapping("/admin/statistical")
    public String adminPage(Model model){
        return "adminPage";
    }
    
    @RequestMapping("/admin/admincategorystats")
    public String adminCategoyStats(Model model)
    {
        model.addAttribute("categoryPostStats", this.statsService.categoryPostStats());
        return "categoryStats";
    }
    @RequestMapping("/admin/adminpoststats")
    public String adminPostStats(Model model , @RequestParam(required=false)Map<String,String> params)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String kw = params.getOrDefault("kw",null);
        
        Date fromDate = null, toDate = null;
        try{
            String fromDateS = params.getOrDefault("fromDate",null);
           if(fromDateS != null )
               fromDate = format.parse(fromDateS);

           String toDateS = params.getOrDefault("toDate",null);
           if(toDateS != null )
               toDate = format.parse(toDateS);
        
        }catch(ParseException ex)
        {
            ex.printStackTrace();
        }
       
        
        model.addAttribute("postStats" , this.statsService.postStats(kw,fromDate,toDate));
        return "postStats";
    }
    @RequestMapping("/admin/adminreportstats")
    public String adminReportRadarChart(Model model)
    {
        model.addAttribute("reportPostChart", this.statsService.reportPostStats());
        model.addAttribute("reportCommentChart", this.statsService.reportCommentStats());
        return "reportStats";
    }
    
    @RequestMapping("/admin/adminlikestats")
    public String likeStats(Model model)
    {
        model.addAttribute("likeStats" , this.statsService.likeStats());
        return "likeStats";
    }
    @RequestMapping("/admin/admincommentstats")
    public String commentStats(Model model)
    {
        model.addAttribute("commentStats" , this.statsService.commentsStats());
        return "commentStats";
    }
    @RequestMapping("/admin/admincommentdaymonthstats")
    public String commentDayMonthStats(Model model, @RequestParam(required=false)Map<String,String> params)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String kw = params.getOrDefault("kw",null);
        
        Date fromDate = null, toDate = null;
        try{
            String fromDateS = params.getOrDefault("fromDate",null);
           if(fromDateS != null )
               fromDate = format.parse(fromDateS);

           String toDateS = params.getOrDefault("toDate",null);
           if(toDateS != null )
               toDate = format.parse(toDateS);
        
        }catch(ParseException ex)
        {
            ex.printStackTrace();
        }
        model.addAttribute("commentDayMonthStats", this.statsService.commentDayMonthStat(kw,fromDate,toDate));
        return"commentDayMonthStats";
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
