package com.msa.minibankaccount.dto.request;

import java.math.BigDecimal;

public record RegisterAccountRequest(Long accountNumber, String accountName, Long customerId, String transferBranch, BigDecimal initialBalance) {
}
