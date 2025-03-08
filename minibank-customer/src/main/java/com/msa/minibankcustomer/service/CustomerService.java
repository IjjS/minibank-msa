package com.msa.minibankcustomer.service;

import com.msa.minibankcustomer.dto.request.CreateCustomerRequest;
import com.msa.minibankcustomer.dto.request.UpdateCustomerRequest;
import com.msa.minibankcustomer.dto.response.CustomerDetailResponse;
import com.msa.minibankcustomer.dto.response.IdResponse;
import com.msa.minibankcustomer.dto.response.SimpleCustomerResponse;

import java.util.List;

public interface CustomerService {

    IdResponse createCustomer(CreateCustomerRequest request);

    SimpleCustomerResponse retrieveCustomer(Long id);

    CustomerDetailResponse retrieveCustomerDetail(Long id);

    List<SimpleCustomerResponse> retrieveCustomers();

    SimpleCustomerResponse updateCustomer(Long id, UpdateCustomerRequest update);

    Boolean existsCustomer(Long id);

}
