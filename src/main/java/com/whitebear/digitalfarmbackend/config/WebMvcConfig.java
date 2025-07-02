//package com.whitebear.digitalfarmbackend.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.whitebear.digitalfarmbackend.interceptor.RoleAuthorizationInterceptor;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private RoleAuthorizationInterceptor roleAuthorizationInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(roleAuthorizationInterceptor)
//                .addPathPatterns("/api/**")
//                .excludePathPatterns("/api/login", "/api/register");
//    }
//}