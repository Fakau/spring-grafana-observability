package com.engine.fakau.stock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${service.payment.url}")
    private String servicePaymentUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .baseUrl(servicePaymentUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

}
