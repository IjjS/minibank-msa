package com.msa.minibanktransfer.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WithdrawalRequest(Long accountNumber, Long customerId, BigDecimal transferAmount, String transferBranch) {
}
