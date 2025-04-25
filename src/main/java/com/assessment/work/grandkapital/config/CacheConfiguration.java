package com.assessment.work.grandkapital.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    @Primary
    public CacheManager phones() {
        return new ConcurrentMapCacheManager();
    }

    @Bean
    public CacheManager users() {
        return new ConcurrentMapCacheManager();
    }
}