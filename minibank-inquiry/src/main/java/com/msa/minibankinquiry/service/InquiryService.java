package com.msa.minibankinquiry.service;

import com.msa.minibankinquiry.dto.AccountDto;
import com.msa.minibankinquiry.dto.CustomerDto;
import com.msa.minibankinquiry.dto.response.CustomerDetailResponse;

public interface InquiryService {

    void createCustomer(CustomerDto customerDto);

    void createAccount(AccountDto accountDto);

    CustomerDetailResponse getCustomerDetail(Long customerId);

}
