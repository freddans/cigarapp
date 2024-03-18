package com.fredrik.cigarapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") // Define the URL pattern to serve resources
                .addResourceLocations("classpath:/static/images/"); // Define the location of the resources
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
    }
}