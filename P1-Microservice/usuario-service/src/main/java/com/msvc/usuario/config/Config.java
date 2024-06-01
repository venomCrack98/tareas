package com.msvc.usuario.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    @LoadBalanced //balanceo de cargas
    public RestTemplate restTemaplte(){
        return new RestTemplate();
    }
}
