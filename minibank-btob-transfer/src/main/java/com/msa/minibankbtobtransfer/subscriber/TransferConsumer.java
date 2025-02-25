package com.msa.minibankbtobtransfer.subscriber;

import com.msa.minibankbtobtransfer.config.kafka.Topics;
import com.msa.minibankbtobtransfer.dto.TransferRequest;
import com.msa.minibankbtobtransfer.dto.TransferResult;
import com.msa.minibankbtobtransfer.publisher.TransferProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransferConsumer {

    private final TransferProducer transferProducer;

    @KafkaListener(topics = "${kafka-config.properties.topics.btob-transfer}", containerFactory = "transferContainerFactory")
    public void bankToBankTransferListener(TransferRequest request, Acknowledgment ack) {
        try {
            log.info("타행 이체 중 ...");
            Thread.sleep(5_000);

            TransferResult result = TransferResult.from(request);

            log.info(Objects.requireNonNullElse(result.isFailed(), true) ? "송금 취소 시도" : "입금 완료");
            transferProducer.sendTransferResult(result);
            ack.acknowledge();
        } catch (Exception exception) {
            log.info("이체 실패: " + exception.getMessage());

            TransferResult failed = TransferResult.from(request.failed());

            log.info("송금 취소 시도");
            transferProducer.sendTransferResult(failed);
        }
    }

}
