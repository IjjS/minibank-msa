package com.msa.minibanktransfer.config.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka-config")
@RequiredArgsConstructor
public class KafkaConfig {

    private final Properties properties;

    public String getBToBTransferTopic() {
        return properties.topics().btobTransfer();
    }

    public String getBToBTransferResultTopic() {
        return properties.topics().btobTransferResult();
    }

    public String getTransferResultGroup() {
        return properties.groups().transferResult();
    }

}
