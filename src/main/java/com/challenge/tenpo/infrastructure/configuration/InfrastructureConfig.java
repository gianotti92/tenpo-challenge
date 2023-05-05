package com.challenge.tenpo.infrastructure.configuration;

import com.challenge.tenpo.application.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class InfrastructureConfig {
    
    @Bean
    public PercentageClient getMockPercentageClient() {
        return new PercentageMockClient();
    }
    
    private class PercentageMockClient implements PercentageClient{


        @Override
        public Integer obtainPercentage(Addition addition) {
            Double result = addition.getSecondAddend() + addition.getSecondAddend();
            return result.intValue();
        }
    }
}
