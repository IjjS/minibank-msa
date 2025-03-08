package com.msa.minibankcustomer.config.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka-config")
@RequiredArgsConstructor
public class KafkaConfig {

    private final Properties properties;

    public String getCreateTopic() {
        return properties.topics().create();
    }

    public String getUpdateTopic() {
        return properties.topics().update();
    }

}
