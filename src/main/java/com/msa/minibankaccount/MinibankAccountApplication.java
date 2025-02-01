package com.msa.minibankaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MinibankAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinibankAccountApplication.class, args);
    }

}
