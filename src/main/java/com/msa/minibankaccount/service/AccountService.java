package com.msa.minibankaccount.service;

import com.msa.minibankaccount.dto.AccountDto;
import com.msa.minibankaccount.dto.AccountNumberResponse;

import java.util.List;

public interface AccountService {

    AccountNumberResponse register(AccountDto request);

    List<AccountDto> retrieveAll();

    AccountDto retrieveOne(Long accountNumber);

}
