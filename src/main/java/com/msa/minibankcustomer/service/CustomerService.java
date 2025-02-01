package com.msa.minibankcustomer.service;

import com.msa.minibankcustomer.dto.CustomerDto;
import com.msa.minibankcustomer.dto.IdResponse;

import java.util.List;

public interface CustomerService {

    IdResponse createCustomer(CustomerDto request);

    CustomerDto retrieveCustomer(Long id);

    List<CustomerDto> retrieveCustomers();

    CustomerDto updateCustomer(Long id, CustomerDto update);

    Boolean existsCustomer(Long id);

}
