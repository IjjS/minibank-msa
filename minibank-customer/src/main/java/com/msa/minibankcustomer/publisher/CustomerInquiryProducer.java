package com.msa.minibankcustomer.publisher;

import com.msa.minibankcustomer.config.kafka.KafkaConfig;
import com.msa.minibankcustomer.dto.CustomerInquiryDto;
import com.msa.minibankcustomer.exception.SystemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerInquiryProducer {

    private final KafkaConfig kafkaConfig;
    private final KafkaTemplate<String, CustomerInquiryDto> customerInquiryKafkaTemplate;

    public void sendCreatedCustomer(CustomerInquiryDto customerInquiry) {
        CompletableFuture<SendResult<String, CustomerInquiryDto>> future = customerInquiryKafkaTemplate.send(kafkaConfig.getCreateTopic(), customerInquiry);

        future.whenComplete((result, exception) -> {
            if (Objects.nonNull(exception)) {
                throw new SystemException("타행이체 실패");
            }

            log.info(result.toString());
        });
    }

}
