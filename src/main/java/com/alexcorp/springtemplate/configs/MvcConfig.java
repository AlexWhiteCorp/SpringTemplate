package com.alexcorp.springtemplate.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/public/images/favicon.ico");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/public/images/");
        registry.addResourceHandler("/styles/**").addResourceLocations("classpath:/static/public/styles/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:/static/public/scripts/");
    }
}