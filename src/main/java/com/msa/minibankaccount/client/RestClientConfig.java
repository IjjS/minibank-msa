package com.msa.minibankaccount.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
