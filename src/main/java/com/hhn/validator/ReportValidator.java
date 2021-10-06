/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.validator;

import com.hhn.pojos.ReportPost;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Windows 10
 */
@Component
public class ReportValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
       return ReportPost.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReportPost r = (ReportPost)target;
        if(r.getUserSendReport().getId() == r.getPostReport().getUser().getId())
            errors.rejectValue("userSendReport", "reportPost.sendReport.Error");
        if(!r.getReasonReport().contains("DHT"))
            errors.rejectValue("reasonReport", "reportPost.sendReport.Error");
    }
    
}
