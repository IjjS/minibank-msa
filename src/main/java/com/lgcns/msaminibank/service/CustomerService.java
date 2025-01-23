package com.lgcns.msaminibank.service;

import com.lgcns.msaminibank.dto.CustomerDto;

public interface CustomerService {

    Integer createCustomer(CustomerDto request);

    CustomerDto retrieveCustomer(Integer id);

    Boolean existsCustomer(Integer id);

}
