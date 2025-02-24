package com.msa.minibankbtobtransfer.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransferResult(Long customerId, Long customerSequence, Long accountSequence, Long depositAccountNumber, Long withdrawalAccountNumber, String receiverMemo, String senderMemo, String receiverCustomerName, BigDecimal transferAmount, Boolean isFailed) {

    public static TransferResult from(TransferRequest request) {
        return TransferResult.builder()
                .customerId(request.customerId())
                .customerSequence(request.customerSequence())
                .accountSequence(request.accountSequence())
                .depositAccountNumber(request.depositAccountNumber())
                .withdrawalAccountNumber(request.withdrawalAccountNumber())
                .receiverMemo(request.receiverMemo())
                .senderMemo(request.senderMemo())
                .receiverCustomerName(request.receiverCustomerName())
                .transferAmount(request.transferAmount())
                .isFailed(request.isFailed())
                .build();
    }

}
