package com.msa.minibankbtobtransfer.config.kafka;

import com.msa.minibankbtobtransfer.dto.TransferResult;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    Map<String, Object> producerConfigProps() {
        Map<String, Object> props = new HashMap<>();
        KafkaProperties.Producer producer = kafkaProperties.getProducer();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producer.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producer.getValueSerializer());

        return props;
    }

    @Bean
    ProducerFactory<String, TransferResult> transferProducerFactory(Map<String, Object> producerConfigProps) {
        return new DefaultKafkaProducerFactory<>(producerConfigProps);
    }

    @Bean
    KafkaTemplate<String, TransferResult> transferKafkaTemplate(ProducerFactory<String, TransferResult> transferProducerFactory) {
        return new KafkaTemplate<>(transferProducerFactory);
    }

}
