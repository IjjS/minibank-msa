package com.msa.minibanktransfer.publisher;

import com.msa.minibanktransfer.config.kafka.Topics;
import com.msa.minibanktransfer.dto.response.TransactionResult;
import com.msa.minibanktransfer.exception.SystemException;
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

    private final KafkaTemplate<String, TransactionResult> transactionResultKafkaTemplate;
    private final Topics topics;

    public void sendBToBTransfer(TransactionResult transactionResult) {
        CompletableFuture<SendResult<String, TransactionResult>> future = transactionResultKafkaTemplate.send(topics.getBtobTransfer(), transactionResult);

        future.whenComplete((result, exception) -> {
            if (Objects.nonNull(exception)) {
                throw new SystemException("타행이체 실패");
            }

            log.info(result.toString());
        });
    }

}
