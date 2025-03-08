package com.msa.minibankinquiry.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Account(Long accountNumber, String accountName, BigDecimal accountBalance, LocalDateTime newDateTime) {

}
