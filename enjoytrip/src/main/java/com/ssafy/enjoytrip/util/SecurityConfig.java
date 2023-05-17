package com.ssafy.enjoytrip.util;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
public class SecurityConfig {
//    @Bean
//    public FilterRegistrationBean<JwtTokenFilter> filter(){
//        FilterRegistrationBean<JwtTokenFilter> bean = new FilterRegistrationBean<>(new JwtTokenFilter());
//        bean.addUrlPatterns("/*");
//        bean.setOrder(0);
//        return bean;
//    }
}
