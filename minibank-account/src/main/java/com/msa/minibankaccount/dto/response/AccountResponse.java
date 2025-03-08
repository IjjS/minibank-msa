package com.msa.minibankaccount.dto.response;

import com.msa.minibankaccount.domain.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(Long accountNumber, String accountName, Long customerId, String customerName, BigDecimal accountBalance, LocalDateTime newDateTime) {

    public static AccountResponse from(Account account) {
        return new AccountResponse(account.getAccountNumber(), account.getAccountName(), account.getCustomerId(), account.getCustomerName(), account.getAccountBalance(), account.getNewDateTime());
    }

}
