package com.msa.minibankbtobtransfer.publisher;

import com.msa.minibankbtobtransfer.config.kafka.Topics;
import com.msa.minibankbtobtransfer.dto.TransferResult;
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
public class TransferProducer {

    private final KafkaTemplate<String, TransferResult> transferKafkaTemplate;
    private final Topics topics;

    public void sendTransferResult(TransferResult transferResult) {
        CompletableFuture<SendResult<String, TransferResult>> future = transferKafkaTemplate.send(topics.getBtobTransferResult(), transferResult);

        future.whenComplete((result, cause) -> {
            if (Objects.nonNull(cause)) {
                log.info("메세지 발행 실패: " + cause.getMessage());
            }
        });
    }
}
