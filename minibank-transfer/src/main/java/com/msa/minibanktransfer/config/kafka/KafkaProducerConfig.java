package com.msa.minibanktransfer.config.kafka;

import com.msa.minibanktransfer.dto.request.BankToBankTransferRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    Map<String, Object> producerConfigProps() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getProducer().getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getProducer().getValueSerializer());

        return props;
    }

    @Bean
    ProducerFactory<String, BankToBankTransferRequest> bankToBankTransferProducerFactory(Map<String, Object> producerConfigProps) {
        return new DefaultKafkaProducerFactory<>(producerConfigProps);
    }

    @Bean
    KafkaTemplate<String, BankToBankTransferRequest> bankToBankKafkaTemplate(ProducerFactory<String, BankToBankTransferRequest> bankToBankTransferProducerFactory) {
        return new KafkaTemplate<>(bankToBankTransferProducerFactory);
    }

}
