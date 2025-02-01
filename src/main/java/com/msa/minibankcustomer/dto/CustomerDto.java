package com.msa.minibankcustomer.dto;

import com.msa.minibankcustomer.domain.Customer;

public record CustomerDto(Long id, String name, String age, String gender, String phoneNumber, String address) {

    public static CustomerDto from(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(), customer.getAge(), customer.getGender(), customer.getPhoneNumber(), customer.getAddress());
    }
}
