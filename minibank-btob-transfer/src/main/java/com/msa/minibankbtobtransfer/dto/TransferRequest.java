package com.msa.minibankbtobtransfer.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransferRequest(Long customerId, Long customerSequence, Long accountSequence, Long depositAccountNumber, Long withdrawalAccountNumber, String receiverMemo, String senderMemo, String receiverCustomerName, BigDecimal transferAmount, Boolean isFailed) {

    public TransferRequest failed() {
        return TransferRequest.builder()
                .customerId(this.customerId)
                .customerSequence(this.customerSequence)
                .accountSequence(this.accountSequence)
                .depositAccountNumber(this.depositAccountNumber)
                .withdrawalAccountNumber(this.withdrawalAccountNumber)
                .receiverMemo(this.receiverMemo)
                .senderMemo(this.senderMemo)
                .receiverCustomerName(this.receiverCustomerName)
                .transferAmount(this.transferAmount)
                .isFailed(false)
                .build();
    }

}
