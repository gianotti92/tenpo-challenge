package com.challenge.tenpo.infrastructure.configuration;

import com.challenge.tenpo.infrastructure.client.PercentageRestResponseDto;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableScheduling
public class CachingConfig {
    
    @Bean
    public Map<Double, PercentageRestResponseDto> cacheMap() {
        Map<Double, PercentageRestResponseDto> cacheResponse = new HashMap<>();
        return cacheResponse;
    }

    @Scheduled(fixedRate = 1800000) //every 30min
    //@Scheduled(fixedRate = 60000) //every 1min
    public void resetMyBean() {
        cacheMap().clear();
    }
    
   
}
