package com.msa.minibanktransfer.dto.response;

import java.math.BigDecimal;

public record TransactionResult(Long accountNumber, Long sequence, BigDecimal transferAmount, BigDecimal previousBalance, BigDecimal currentBalance, Boolean isFailed) {
}
