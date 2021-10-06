/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.formatter;

import com.hhn.pojos.Comments;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Windows 10
 */
public class CommentFormatter implements Formatter<Comments>{

    @Override
    public String print(Comments t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Comments parse(String commentID, Locale locale) throws ParseException {
        Comments c = new Comments();
        c.setId(Integer.parseInt(commentID));
        return c;
    }
    
}
