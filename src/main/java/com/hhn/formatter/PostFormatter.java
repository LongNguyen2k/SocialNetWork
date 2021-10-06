/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.formatter;

import com.hhn.pojos.Post;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Windows 10
 */
public class PostFormatter implements Formatter<Post>{

    @Override
    public String print(Post t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Post parse(String postID, Locale locale) throws ParseException {
        Post post = new Post();
        post.setId(Integer.parseInt(postID));
        return post;
    }
    
}
