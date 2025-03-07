package com.msa.minibanktransfer.dto.request;

import java.math.BigDecimal;

public record TransferRequest(String bankCode, Long customerId, Long depositAccountNumber, Long withdrawalAccountNumber, String receiverMemo, String senderMemo, String receiverCustomerName, BigDecimal transferAmount, Boolean isFailed) {
}
