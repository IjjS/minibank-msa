package com.msa.minibanktransfer.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BankToBankTransferRequest(String bankCode, Long customerId, Long customerSequence, Long accountSequence, Long depositAccountNumber, Long withdrawalAccountNumber, String receiverMemo, String senderMemo, String receiverCustomerName, BigDecimal transferAmount, Boolean isFailed) {

    public static BankToBankTransferRequest from(TransferRequest request, Long customerSequence, Long accountSequence) {
        return BankToBankTransferRequest.builder()
                .bankCode(request.bankCode())
                .customerId(request.customerId())
                .customerSequence(customerSequence)
                .accountSequence(accountSequence)
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
