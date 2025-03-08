package com.msa.minibanktransfer.subscriber;

import com.msa.minibanktransfer.dto.response.BankToBankTransferResult;
import com.msa.minibanktransfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class B2BTransferResultConsumer {

    private final TransferService transferService;

    @KafkaListener(topics = "${kafka-config.properties.topics.btob-transfer-result}", containerFactory = "bankToBankTransferResultContainerFactory")
    public void bankToBankTransferResultListener(BankToBankTransferResult transferResult, Acknowledgment ack) {
        transferService.applyTransferResult(transferResult);
        ack.acknowledge();
    }

}
