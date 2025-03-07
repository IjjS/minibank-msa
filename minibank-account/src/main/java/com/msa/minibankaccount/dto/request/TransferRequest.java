package com.msa.minibankaccount.dto.request;

import java.math.BigDecimal;

public record TransferRequest(Long accountNumber, Long customerId, BigDecimal transferAmount, String transferBranch) {
}
