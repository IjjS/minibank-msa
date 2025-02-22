package com.msa.minibankaccount.service;

import com.msa.minibankaccount.dto.request.RegisterAccountRequest;
import com.msa.minibankaccount.dto.response.AccountNumberResponse;
import com.msa.minibankaccount.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {

    AccountNumberResponse register(RegisterAccountRequest request);

    List<AccountResponse> retrieveAllOfCustomer(Long customerId);

    AccountResponse retrieveOne(Long accountNumber);

}
