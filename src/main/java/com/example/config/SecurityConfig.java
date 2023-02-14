package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Resource
//    AuthService service;
    //身份认证登录
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123");

        try {
            auth.inMemoryAuthentication().withUser("zjp").password(password).roles("admin");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Bean
    PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successForwardUrl("/api/auth/login-success")
                .failureForwardUrl("/api/auth/login-failure")
                .permitAll()
                .and()
                .csrf().disable();
    }

    //核心过滤器配置
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
