package com.msa.minibankinquiry.dto.response;

import com.msa.minibankinquiry.domain.Account;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record CustomerAccountResponse(Long accountNumber, String accountName, BigDecimal accountBalance, LocalDateTime newDateTime) {

    public static CustomerAccountResponse from(Account account) {
        return CustomerAccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .accountName(account.getAccountName())
                .newDateTime(account.getNewDateTime())
                .accountBalance(account.getAccountBalance())
                .build();
    }

}
