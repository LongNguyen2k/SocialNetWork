/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.formatter;

import com.hhn.pojos.CategoryPost;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;



/**
 *
 * @author Windows 10
 */
public class CategoryPostFormatter implements Formatter<CategoryPost>{

    @Override
    public String print(CategoryPost t, Locale locale) {
       return String.valueOf(t.getId());
    }

    @Override
    public CategoryPost parse(String cateId, Locale locale) throws ParseException {
        CategoryPost c = new CategoryPost();
        c.setId(Integer.parseInt(cateId));
        return c;
    }

    

   
    
}
