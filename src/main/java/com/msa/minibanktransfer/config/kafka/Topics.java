package com.msa.minibanktransfer.config.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("topics")
@RequiredArgsConstructor
@Getter
public class Topics {

    private final String btobTransfer;

}
