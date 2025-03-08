package com.msa.minibankgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MinibankGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinibankGatewayApplication.class, args);
    }

}
