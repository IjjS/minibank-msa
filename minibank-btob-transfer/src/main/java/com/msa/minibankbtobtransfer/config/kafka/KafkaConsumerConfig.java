package com.msa.minibankbtobtransfer.config.kafka;

import com.msa.minibankbtobtransfer.dto.TransferRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;
    private final KafkaConfig kafkaConfig;

    @Bean
    Map<String, Object> consumerConfigProps() {
        Map<String, Object> props = new HashMap<>();
        KafkaProperties.Consumer consumer = kafkaProperties.getConsumer();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumer.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumer.getValueDeserializer());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumer.getAutoOffsetReset());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumer.getEnableAutoCommit());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfig.getBToBTransferGroup());

        return props;
    }

    @Bean
    ConsumerFactory<String, TransferRequest> transferConsumerFactory(Map<String, Object> consumerConfigProps) {
        return new DefaultKafkaConsumerFactory<>(consumerConfigProps, new StringDeserializer(), new JsonDeserializer<>(TransferRequest.class, false));
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, TransferRequest> transferContainerFactory(ConsumerFactory<String, TransferRequest> transferConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, TransferRequest> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        KafkaProperties.Listener listener = kafkaProperties.getListener();

        containerFactory.setConsumerFactory(transferConsumerFactory);
        containerFactory.getContainerProperties().setAckMode(listener.getAckMode());
        containerFactory.setCommonErrorHandler(new DefaultErrorHandler());

        return containerFactory;
    }
}
