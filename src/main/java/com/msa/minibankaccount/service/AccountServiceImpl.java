package com.msa.minibankaccount.service;

import com.msa.minibankaccount.client.customer.CustomerDto;
import com.msa.minibankaccount.client.customer.CustomerRestClient;
import com.msa.minibankaccount.domain.Account;
import com.msa.minibankaccount.dto.AccountDto;
import com.msa.minibankaccount.dto.AccountNumberResponse;
import com.msa.minibankaccount.exception.BusinessException;
import com.msa.minibankaccount.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;

    @Override
    public AccountNumberResponse register(AccountDto request) {
        if (accountRepository.existsById(request.accountNumber())) {
            throw new BusinessException("계좌 중복");
        }

        CustomerDto customer = customerRestClient.retrieveCustomer(request.customerId());
        Account account = new Account(request.accountNumber(), request.accountName(), customer.id(), customer.name(), request.accountBalance(), request.newDateTime());
        Account saved = accountRepository.save(account);

        return new AccountNumberResponse(saved.getAccountNumber());
    }

    @Override
    public List<AccountDto> retrieveAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::from)
                .toList();
    }

    @Override
    public AccountDto retrieveOne(Long accountNumber) {
        return accountRepository.findById(accountNumber)
                .map(AccountDto::from)
                .orElseThrow(() -> new BusinessException("계좌 없음"));
    }

}
