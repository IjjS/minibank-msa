package com.msa.minibankeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MinibankEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinibankEurekaApplication.class, args);
    }

}
