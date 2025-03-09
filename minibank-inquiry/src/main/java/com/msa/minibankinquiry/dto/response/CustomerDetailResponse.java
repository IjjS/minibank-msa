package com.msa.minibankinquiry.dto.response;

import com.msa.minibankinquiry.domain.CustomerDetail;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CustomerDetailResponse(Long customerId, String customerName, String gender, String phoneNumber, String address, BigDecimal oneDayTransferLimit, BigDecimal oneTimeTransferLimit, List<CustomerAccountResponse> accounts) {

    public static CustomerDetailResponse from(CustomerDetail customer) {
        List<CustomerAccountResponse> accounts = customer.getAccounts()
                .stream()
                .map(CustomerAccountResponse::from)
                .toList();

        return CustomerDetailResponse.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .gender(customer.getGender())
                .address(customer.getAddress())
                .phoneNumber(customer.getPhoneNumber())
                .oneTimeTransferLimit(customer.getOneTimeTransferLimit())
                .oneDayTransferLimit(customer.getOneDayTransferLimit())
                .accounts(accounts)
                .build();
    }

}
