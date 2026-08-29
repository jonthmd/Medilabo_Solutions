package com.medilabo.front.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign configuration that automatically applies HTTP Basic Authentication to requests.
 */
@Configuration
public class FeignConfig {

    @Bean
    public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor()

    {
        return  new BasicAuthRequestInterceptor("user", "user");
    }
}
