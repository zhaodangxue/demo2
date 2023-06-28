//package com.example.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import com.example.demo.Interceptor.JwtInterceptor;
//@Configuration
//public class JwtConfig implements WebMvcConfigurer{
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JwtInterceptor())
//                //拦截所有请求
//                .addPathPatterns("/user")
//                //放行的请求
//                .excludePathPatterns("/user/login").excludePathPatterns("/websocket/**").excludePathPatterns("/websocket");
//    }
//}
