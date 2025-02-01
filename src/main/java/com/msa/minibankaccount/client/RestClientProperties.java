package com.msa.minibankaccount.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("client-api")
@RequiredArgsConstructor
@Getter
public class RestClientProperties {

    private final String customerUrl;

}
