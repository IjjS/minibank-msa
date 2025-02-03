package com.msa.minibankcustomer.dto.response;

import com.msa.minibankcustomer.domain.Customer;
import lombok.Builder;

@Builder
public record SimpleCustomerResponse(Long id, String name, Integer age, String gender, String phoneNumber, String address) {

    public static SimpleCustomerResponse from(Customer customer) {
        return SimpleCustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .gender(customer.getGender())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }

}
