package com.msa.minibankaccount.dto.response;

import com.msa.minibankaccount.domain.TransactionHistory;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionResultResponse(Long accountNumber, Long sequence, BigDecimal transferAmount, BigDecimal previousBalance, BigDecimal currentBalance) {

    public static TransactionResultResponse from(TransactionHistory transactionHistory, BigDecimal previousBalance) {
        return TransactionResultResponse.builder()
                .accountNumber(transactionHistory.getAccountNumber())
                .sequence(transactionHistory.getSequence())
                .transferAmount(transactionHistory.getTransferAmount())
                .previousBalance(previousBalance)
                .currentBalance(transactionHistory.getAccountBalance())
                .build();
    }

}
