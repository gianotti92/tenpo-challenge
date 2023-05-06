package com.challenge.tenpo.infrastructure.configuration;

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
}
