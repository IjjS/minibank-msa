package com.msa.minibankcustomer.dto.request;

public record CreateCustomerRequest(String name, Integer age, String gender, String phoneNumber, String address) {
}
