package com.msa.minibankcustomer.dto;

import java.math.BigDecimal;

public record TransferLimitDto(BigDecimal oneTimeTransferLimit, BigDecimal oneDayTransferLimit) {
}
