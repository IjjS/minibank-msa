package com.msa.minibankcustomer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDto(Long accountNumber, String accountName, Long customerId, String customerName, BigDecimal accountBalance, LocalDateTime newDateTime) {
}
