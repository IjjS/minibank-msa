package com.msa.minibankaccount.config.kafka;

import com.msa.minibankaccount.dto.CustomerInquiryAccountDto;
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

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getProducer().getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getProducer().getValueSerializer());

        return props;
    }

    @Bean
    ProducerFactory<String, CustomerInquiryAccountDto> customerInquiryAccountProducerFactory(Map<String, Object> producerConfigProps) {
        return new DefaultKafkaProducerFactory<>(producerConfigProps);
    }

    @Bean
    KafkaTemplate<String, CustomerInquiryAccountDto> customerInquiryAccountKafkaTemplate(ProducerFactory<String, CustomerInquiryAccountDto> customerInquiryAccountProducerFactory) {
        return new KafkaTemplate<>(customerInquiryAccountProducerFactory);
    }

}
