/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.controllers;

import com.hhn.pojos.Post;
import com.hhn.pojos.ReportComment;
import com.hhn.pojos.ReportPost;
import com.hhn.pojos.User;
import com.hhn.service.PostService;
import com.hhn.service.ReportService;
import com.hhn.service.UserService;
import com.hhn.validator.WebAppValidator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Windows 10
 */
@Controller
public class ReportController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private WebAppValidator reportValidator;
    @InitBinder(value = "reportValidator")
    public void initBinder(WebDataBinder binder)
    {
        binder.setValidator(reportValidator);
    }
    
    @RequestMapping(value = "/user/SendReportPage" )
    public String formReportPost(Model model , @RequestParam(required = false)Map<String,String> params)
    {
       int postSelectedReport = Integer.parseInt(params.get("postID"));
       String username = params.get("username");
       model.addAttribute("postSelectedReport", postSelectedReport);
       model.addAttribute("userSendReport",this.userService.getCurrentLoggedInUser(username).get(0));
       model.addAttribute("adminReceiveReport", this.userService.getListAdminRole());
       model.addAttribute("ReportPost", new ReportPost());
        return "sendReportPage";
    }
    @PostMapping(value = "/user/sendReport")
    public String sendReportToAdmin(Model model , @ModelAttribute(value = "ReportPost")@Valid ReportPost reportPost 
            , BindingResult result , HttpServletRequest request , 
            @ModelAttribute(value = "postSelectedReport")String postSelectedReport)
    {
        model.addAttribute("adminReceiveReport", this.userService.getListAdminRole());
        if(!result.hasErrors())
        {
                if(this.reportService.addReport(reportPost))
                    return "redirect:/user/confirmreportPage";
                else
                    model.addAttribute("errMsG","Đã có lỗi xảy ra trong lúc thêm dữ liệu !");

        }
        return "sendReportPage";
    }
    @RequestMapping(value = "/user/SendReportCommentPage")
    public String formReportComment(Model model,@RequestParam(required = false)Map<String,String> params)
    {
        int commentSelectedReport = Integer.parseInt(params.get("commentID"));
        String username = params.get("username");
        model.addAttribute("commentSelectedReport", commentSelectedReport);
        model.addAttribute("userSendReport",this.userService.getCurrentLoggedInUser(username).get(0));
        model.addAttribute("adminReceiveReport" , this.userService.getListAdminRole() );
        model.addAttribute("ReportComment" , new ReportComment());
        return "sendReportCommentPage";
    }
    @PostMapping(value = "/user/sendReportComment")
    public String sendReportCommentToAdmin(Model model ,@ModelAttribute(value = "ReportComment")@Valid ReportComment reportComment ,
        BindingResult result , HttpServletRequest request ,
        @ModelAttribute(value = "commentSelectedReport")String commentSelectedReport )
    {
        model.addAttribute("adminReceiveReport", this.userService.getListAdminRole());
        
        if(!result.hasErrors())
        {
            if(this.reportService.addReportComment(reportComment))
                return "redirect:/user/confirmreportPage";
            else
                model.addAttribute("errMsG","Đã có lỗi xảy ra trong lúc thêm dữ liệu !");
        }
         return "sendReportCommentPage";
    }
    
    @RequestMapping(value = "/user/confirmreportPage")
    public String confirmReport(Model model)
    {
        return "confirmReport";
    }
    
    
}
