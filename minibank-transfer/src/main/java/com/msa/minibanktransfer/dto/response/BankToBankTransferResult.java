package com.msa.minibanktransfer.dto.response;

import java.math.BigDecimal;

public record BankToBankTransferResult(Long customerId, Long customerSequence, Long accountSequence, Long depositAccountNumber, Long withdrawalAccountNumber, String receiverMemo, String senderMemo, String receiverCustomerName, BigDecimal transferAmount, Boolean isFailed) {
}
