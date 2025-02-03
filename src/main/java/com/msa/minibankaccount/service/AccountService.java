package com.msa.minibankaccount.service;

import com.msa.minibankaccount.dto.AccountDto;
import com.msa.minibankaccount.dto.AccountNumberResponse;
import com.msa.minibankaccount.dto.RegisterAccountRequest;

import java.util.List;

public interface AccountService {

    AccountNumberResponse register(RegisterAccountRequest request);

    List<AccountDto> retrieveAllOfCustomer(Long customerId);

    AccountDto retrieveOne(Long accountNumber);

}
