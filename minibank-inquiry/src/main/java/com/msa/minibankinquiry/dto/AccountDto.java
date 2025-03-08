package com.msa.minibankinquiry.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDto(Long accountNumber, String accountName, BigDecimal accountBalance, LocalDateTime newDateTime) {
}
