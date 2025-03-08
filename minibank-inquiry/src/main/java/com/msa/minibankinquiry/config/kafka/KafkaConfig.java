package com.msa.minibankinquiry.config.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka-config")
@RequiredArgsConstructor
public class KafkaConfig {

    private final Properties properties;

    public String getCustomerGroup() {
        return properties.groups().customer();
    }

    public String getAccountGroup() {
        return properties.groups().account();
    }

    public String getCustomerCreateTopic() {
        return properties.topics().customer().create();
    }

    public String getCustomerUpdateTopic() {
        return properties.topics().customer().update();
    }

    public String getAccountCreateTopic() {
        return properties.topics().account().create();
    }

    public String getAccountUpdateTopic() {
        return properties.topics().account().update();
    }

}
