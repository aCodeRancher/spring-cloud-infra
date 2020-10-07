package com.codespacelab.zuul;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public ZuulAuthFilter getZuulAuthFilter(){
        return new ZuulAuthFilter();
    }
}
