package com.msa.minibankinquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MinibankInquiryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinibankInquiryApplication.class, args);
	}

}
