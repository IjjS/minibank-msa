package com.msa.minibankaccount.publisher;

import com.msa.minibankaccount.config.kafka.KafkaConfig;
import com.msa.minibankaccount.dto.CustomerInquiryAccountDto;
import com.msa.minibankaccount.exception.SystemException;
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
public class CustomerInquiryAccountProducer {

    private final KafkaConfig kafkaConfig;
    private final KafkaTemplate<String, CustomerInquiryAccountDto> customerInquiryAccountKafkaTemplate;

    public void sendCreatedAccount(CustomerInquiryAccountDto account) {
        CompletableFuture<SendResult<String, CustomerInquiryAccountDto>> future = customerInquiryAccountKafkaTemplate.send(kafkaConfig.getCreateTopic(), account);

        future.whenComplete((result, exception) -> {
            if (Objects.nonNull(exception)) {
                throw new SystemException("타행이체 실패");
            }

            log.info(result.toString());
        });
    }

}
