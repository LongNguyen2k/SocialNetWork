/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Windows 10
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // khởi động dispatcher servlet 
    // kế thừa lớp trừu tượng dùng để chỉ dinh dispatcher servlet của spring mvc được cấu hình bằng annotation
    // tập tin khởi chạy đầu tiên của ứng dụng 
    // sẽ tiến hành quét ServletConfigClass để lấy các bean trong nó để chạy project 

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
            WebApplicationContextConfig.class
        };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            TilesConfig.class,
            HibernateConfig.class,
            SpringSecurityConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
