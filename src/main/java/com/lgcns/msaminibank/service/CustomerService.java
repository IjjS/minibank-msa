package com.lgcns.msaminibank.service;

import com.lgcns.msaminibank.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    Integer createCustomer(CustomerDto request);

    CustomerDto retrieveCustomer(Integer id);

    List<CustomerDto> retrieveCustomers();

    CustomerDto updateCustomer(Integer cstmId, CustomerDto update);

    Boolean existsCustomer(Integer id);

}
