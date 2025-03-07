package com.msa.minibankaccount.dto.request;

public record RegisterAccountRequest(Long accountNumber, String accountName, Long customerId, String transferBranch) {
}
