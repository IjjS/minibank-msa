package com.msa.minibankcustomer.dto.response;

import com.msa.minibankcustomer.domain.Customer;
import com.msa.minibankcustomer.dto.AccountDto;
import com.msa.minibankcustomer.dto.TransferLimitDto;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CustomerDetailResponse(Long id, String name, Integer age, String gender, String phoneNumber, String address, BigDecimal oneTimeTransferLimit, BigDecimal oneDayTransferLimit, List<AccountDto> accounts) {

    public static CustomerDetailResponse from(Customer customer, List<AccountDto> accounts, TransferLimitDto transferLimit) {
        return CustomerDetailResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .gender(customer.getGender())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .oneTimeTransferLimit(transferLimit.oneTimeTransferLimit())
                .oneDayTransferLimit(transferLimit.oneDayTransferLimit())
                .accounts(accounts)
                .build();
    }

}
