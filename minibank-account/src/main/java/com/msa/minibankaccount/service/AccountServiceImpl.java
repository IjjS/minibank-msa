package com.msa.minibankaccount.service;

import com.msa.minibankaccount.client.customer.CustomerDto;
import com.msa.minibankaccount.client.customer.CustomerFeignClient;
import com.msa.minibankaccount.client.customer.CustomerRestClient;
import com.msa.minibankaccount.domain.Account;
import com.msa.minibankaccount.domain.DivisionCode;
import com.msa.minibankaccount.domain.StatusCode;
import com.msa.minibankaccount.domain.TransactionHistory;
import com.msa.minibankaccount.dto.CustomerInquiryAccountDto;
import com.msa.minibankaccount.dto.request.RegisterAccountRequest;
import com.msa.minibankaccount.dto.response.AccountNumberResponse;
import com.msa.minibankaccount.dto.response.AccountResponse;
import com.msa.minibankaccount.exception.BusinessException;
import com.msa.minibankaccount.publisher.CustomerInquiryAccountProducer;
import com.msa.minibankaccount.repository.AccountRepository;
import com.msa.minibankaccount.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final Long FIRST_SEQUENCE = 1L;

    private final AccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;
    private final CustomerFeignClient customerFeignClient;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final CustomerInquiryAccountProducer customerInquiryAccountProducer;

    @Override
    @Transactional
    public AccountNumberResponse register(RegisterAccountRequest request) {
        if (accountRepository.existsById(request.accountNumber())) {
            throw new BusinessException("계좌 중복");
        }

        // RestTemplate
//        CustomerDto customer = customerRestClient.retrieveCustomer(request.customerId());

        // OpenFeign
        CustomerDto customer = customerFeignClient.retrieveCustomer(request.customerId());

        Account account = new Account(request.accountNumber(), request.accountName(), customer.id(), customer.name(), request.initialBalance(), LocalDateTime.now());
        CustomerInquiryAccountDto accountForInquiry = CustomerInquiryAccountDto.from(account);

        accountRepository.save(account);
        customerInquiryAccountProducer.sendCreatedAccount(accountForInquiry);

        TransactionHistory firstHistory = TransactionHistory.builder()
                .accountNumber(account.getAccountNumber())
                .sequence(FIRST_SEQUENCE)
                .divisionCode(DivisionCode.DEPOSIT.getCode())
                .transferAmount(account.getAccountBalance())
                .transactionDateTime(LocalDateTime.now())
                .transferBranch(request.transferBranch())
                .accountBalance(account.getAccountBalance())
                .statusCode(StatusCode.SUCCESSFUL.getCode())
                .build();

        transactionHistoryRepository.save(firstHistory);

        return new AccountNumberResponse(account.getAccountNumber());
    }

    @Override
    public List<AccountResponse> retrieveAllOfCustomer(Long customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(AccountResponse::from)
                .toList();
    }

    @Override
    public AccountResponse retrieveOne(Long accountNumber) {
        return accountRepository.findById(accountNumber)
                .map(AccountResponse::from)
                .orElseThrow(() -> new BusinessException("계좌 없음"));
    }

}
