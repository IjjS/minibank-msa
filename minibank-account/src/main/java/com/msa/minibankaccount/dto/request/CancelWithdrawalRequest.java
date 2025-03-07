package com.msa.minibankaccount.dto.request;

import java.math.BigDecimal;

public record CancelWithdrawalRequest(Long customerId, BigDecimal transferAmount) {
}
