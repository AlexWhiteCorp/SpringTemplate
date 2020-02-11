package com.alexcorp.springtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Application {

    @Configuration
    @Profile("dev")
    @PropertySource("classpath:/properties/dev/spring.properties")
    static class Development
    { }

    @Configuration
    @Profile("prod")
    @PropertySource({"classpath:/properties/prod/spring.properties"})
    static class Production
    { }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}