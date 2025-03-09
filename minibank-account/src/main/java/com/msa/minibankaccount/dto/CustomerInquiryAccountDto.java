package com.msa.minibankaccount.dto;

import com.msa.minibankaccount.domain.Account;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record CustomerInquiryAccountDto(Long customerId, Long accountNumber, String accountName, BigDecimal accountBalance, LocalDateTime newDateTime) {

    public static CustomerInquiryAccountDto from(Account account) {
        return CustomerInquiryAccountDto.builder()
                .customerId(account.getCustomerId())
                .accountNumber(account.getAccountNumber())
                .accountName(account.getAccountName())
                .accountBalance(account.getAccountBalance())
                .newDateTime(account.getNewDateTime())
                .build();
    }

}
