package com.challenge.tenpo.infrastructure.configuration;

import com.challenge.tenpo.infrastructure.controller.mapper.AdditionControllerMapper;
import com.challenge.tenpo.infrastructure.controller.mapper.AdditionControllerMapperImpl;
import com.challenge.tenpo.infrastructure.persistence.mapper.ExternalCallPersistenceMapper;
import com.challenge.tenpo.infrastructure.persistence.mapper.ExternalCallPersistenceMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapstructConfig {
    
    @Bean
    public ExternalCallPersistenceMapper getAdditionPersistenceMapper() {
        return new ExternalCallPersistenceMapperImpl();
    }
    
    @Bean
    public AdditionControllerMapper getAdditionControllerMapper() {
        return new AdditionControllerMapperImpl();
    }
}
