package com.msa.minibankinquiry.dto;

import java.math.BigDecimal;

public record CustomerDto(Long customerId, String customerName, String gender, String phoneNumber, String address, BigDecimal oneDayTransferLimit, BigDecimal oneTimeTransferLimit) {

}
