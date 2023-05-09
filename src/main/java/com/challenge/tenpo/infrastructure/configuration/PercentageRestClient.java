package com.challenge.tenpo.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PercentageRestClient {
    
    @Bean
    public RestTemplate getRestTemplate() {
        var restTemplate = new RestTemplate();
        return restTemplate;
        
    }
    
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
