package com.alexcorp.springtemplate.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
public class JpaConfig {

    @Configuration
    @Profile("dev")
    @PropertySource("classpath:/properties/dev/jpa.properties")
    static class Development
    { }

    @Configuration
    @Profile("prod")
    @PropertySource({"classpath:/properties/prod/jpa.properties"})
    static class Production
    { }
}
