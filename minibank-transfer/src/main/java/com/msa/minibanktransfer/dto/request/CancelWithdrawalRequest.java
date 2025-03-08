package com.msa.minibanktransfer.dto.request;

import java.math.BigDecimal;

public record CancelWithdrawalRequest(Long customerId, BigDecimal transferAmount) {
}
