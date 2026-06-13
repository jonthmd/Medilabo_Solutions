package com.medilabo.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configures required component to communicate with API REST.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Creates the HTTP client used to make requests
     * to external REST APIs.
     *
     * @return A RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
