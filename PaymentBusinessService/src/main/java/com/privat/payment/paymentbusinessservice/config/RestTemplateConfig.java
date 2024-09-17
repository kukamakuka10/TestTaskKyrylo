package com.privat.payment.paymentbusinessservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {
    @Value("${spring.service.url}")
    private String baseUrl;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        restTemplate.setUriTemplateHandler(factory);

        return restTemplate;
    }
}
