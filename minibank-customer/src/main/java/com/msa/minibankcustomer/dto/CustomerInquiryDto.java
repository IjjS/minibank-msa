package com.msa.minibankcustomer.dto;

import com.msa.minibankcustomer.domain.Customer;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CustomerInquiryDto(Long customerId, String customerName, String gender, String phoneNumber, String address, BigDecimal oneDayTransferLimit, BigDecimal oneTimeTransferLimit) {
    
    public static CustomerInquiryDto from(Customer customer, TransferLimitDto transferLimits) {
        return CustomerInquiryDto.builder()
                .customerId(customer.getId())
                .customerName(customer.getName())
                .gender(customer.getGender())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .oneDayTransferLimit(transferLimits.oneDayTransferLimit())
                .oneTimeTransferLimit(transferLimits.oneTimeTransferLimit())
                .build();
    }
    
}
