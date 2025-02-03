package com.msa.minibankcustomer.dto.request;

public record UpdateCustomerRequest(Long id, String name, Integer age, String gender, String phoneNumber, String address) {

}
