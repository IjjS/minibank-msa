package com.msa.minibankaccount.dto;

import java.time.LocalDateTime;

public record RegisterAccountRequest(Long accountNumber, String accountName, Long customerId, LocalDateTime newDateTime) {
}
