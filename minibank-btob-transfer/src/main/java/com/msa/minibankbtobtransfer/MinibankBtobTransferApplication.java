package com.msa.minibankbtobtransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableDiscoveryClient
public class MinibankBtobTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinibankBtobTransferApplication.class, args);
	}

}
