package com.kodilla.savings.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


import java.util.Properties;

@Configuration
@EnableScheduling
@EnableCaching
@OpenAPIDefinition
public class CoreConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
