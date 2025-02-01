package com.msa.minibankaccount.dto;

import com.msa.minibankaccount.domain.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDto(Long accountNumber, String accountName, Long customerId, String customerName, BigDecimal accountBalance, LocalDateTime newDateTime) {

    public static AccountDto from(Account account) {
        return new AccountDto(account.getAccountNumber(), account.getAccountName(), account.getCustomerId(), account.getCustomerName(), account.getAccountBalance(), account.getNewDateTime());
    }

}
