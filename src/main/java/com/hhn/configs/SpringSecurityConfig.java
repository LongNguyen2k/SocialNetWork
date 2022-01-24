/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hhn.configs.handlers.LogOutSuccessfulHandler;
import com.hhn.configs.handlers.LoginSucessfulHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Windows 10
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.hhn.service",
    "com.hhn.repository"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired // dữ liệu được lấy từ bean bên dưới 
    private AuthenticationSuccessHandler loginSuccessHandler; // biến này được dùng trong hàm config để nó thực hiện goi sau khi đã login 
    // trong spring security 
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "decmhyieh",
                "api_key", "413756871999116",
                "api_secret", "G34UwtLLRY2hg_PCrmzs5pT9RTo",
                "secure", true
        ));
        return c;
    }

    // tạo ra bean để ioc thực hiên móc nối vào các autowire
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        // phương thức khởi tạo của Login Success trong gói handler
        return new LoginSucessfulHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogOutSuccessfulHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // cấu hình form login 
        // chỉ định end point để spring security thực hiện post vào form này để thực hiện login
        http.formLogin().loginPage("/signin")
                .usernameParameter("username") // 2 trường dữ liệu lấy username và password
                .passwordParameter("password");
        // hàm login mặc định
//        http.formLogin().defaultSuccessUrl("/user/").failureUrl("/signin?error"); // nếu như có lỗi thì thực hiện đứng tại trang login kèm thông tin lỗi
        // sau khi xử lý đăng nhập sẽ thực hiện gọi hàm bên dưới để lưu đối tượng user vào session  
        http.formLogin().successHandler(this.loginSuccessHandler).failureUrl("/signin?error");// thực hiện hàm này 

//        http.logout().logoutSuccessUrl("/signin"); // logout mặc định
        http.logout().logoutSuccessHandler(this.logoutSuccessHandler); // thực hiện hàm này
        
        http.exceptionHandling().accessDeniedPage("/signin?accessDenied");
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
//       http.exceptionHandling().accessDeniedPage("/user/?accessAuthorize");
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web); //To change body of generated methods, choose Tools | Templates.
    }

}

// http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
//               .and().authorizeRequests().antMatchers("/user/**").hasRole("USER")
//               .anyRequest().permitAll().and().formLogin().loginPage("/user/signin").loginProcessingUrl("/user/signin")
//               .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/profile")
//               .failureUrl("/user/signin?error=failed").and().exceptionHandling().accessDeniedPage("/user/signin?error=denied");
