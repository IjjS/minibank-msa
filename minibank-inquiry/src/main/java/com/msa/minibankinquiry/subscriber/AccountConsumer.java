package com.msa.minibankinquiry.subscriber;

import com.msa.minibankinquiry.dto.AccountDto;
import com.msa.minibankinquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountConsumer {

    private final InquiryService inquiryService;

    @KafkaListener(topics = "${kafka-config.properties.topics.account.create}", containerFactory = "accountContainerFactory")
    public void accountCreateListener(AccountDto account, Acknowledgment ack) {
        inquiryService.createAccount(account);
        ack.acknowledge();
    }

}
