package com.msa.minibankinquiry.subscriber;

import com.msa.minibankinquiry.dto.CustomerDto;
import com.msa.minibankinquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConsumer {

    private final InquiryService inquiryService;

    @KafkaListener(topics = "${kafka-config.properties.topics.customer.create}", containerFactory = "customerContainerFactory")
    public void customerCreateListener(CustomerDto customer, Acknowledgment ack) {
        inquiryService.createCustomer(customer);

        ack.acknowledge();
    }

}
