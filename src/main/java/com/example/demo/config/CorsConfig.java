package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Configuration
public class CorsConfig implements WebMvcConfigurer{
     @Override
     public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
                 .allowedOriginPatterns("*")
                 .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                 .allowCredentials(true)
                 .maxAge(3600)
                 .allowedHeaders("*");
     }
}
