package com.challenge.tenpo.infrastructure.configuration;

import com.challenge.tenpo.infrastructure.controller.mapper.AdditionControllerMapper;
import com.challenge.tenpo.infrastructure.controller.mapper.AdditionControllerMapperImpl;
import com.challenge.tenpo.infrastructure.persistence.mapper.AdditionPersistenceMapper;
import com.challenge.tenpo.infrastructure.persistence.mapper.AdditionPersistenceMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapstructConfig {
    
    @Bean
    public AdditionPersistenceMapper getAdditionPersistenceMapper() {
        return new AdditionPersistenceMapperImpl();
    }
    
    @Bean
    public AdditionControllerMapper getAdditionControllerMapper() {
        return new AdditionControllerMapperImpl();
    }
}
