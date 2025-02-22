package com.msa.minibanktransfer.dto.request;

import java.math.BigDecimal;

public record TransferRequest(Long customerId, Long depositAccountNumber, Long withdrawalAccountNumber, String receiverMemo, String senderMemo, String receiverCustomerName, BigDecimal transferAmount) {
}
