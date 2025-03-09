package com.msa.minibankinquiry.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Builder
@Getter
public final class Account {

    private final Long accountNumber;
    private final String accountName;
    private final BigDecimal accountBalance;
    private final LocalDateTime newDateTime;

}
