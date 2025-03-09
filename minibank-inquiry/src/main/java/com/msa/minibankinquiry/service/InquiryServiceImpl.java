package com.msa.minibankinquiry.service;

import com.msa.minibankinquiry.domain.Account;
import com.msa.minibankinquiry.domain.CustomerDetail;
import com.msa.minibankinquiry.dto.AccountDto;
import com.msa.minibankinquiry.dto.CustomerDto;
import com.msa.minibankinquiry.dto.response.CustomerDetailResponse;
import com.msa.minibankinquiry.exception.BusinessException;
import com.msa.minibankinquiry.repository.CustomerDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final CustomerDetailRepository customerDetailRepository;

    @Override
    public void createCustomer(CustomerDto customerDto) {
        CustomerDetail customerDetail = buildCustomerDetail(customerDto);

        customerDetailRepository.save(customerDetail);
    }

    @Override
    public void createAccount(AccountDto accountDto) {
        CustomerDetail customerDetail = customerDetailRepository.findById(accountDto.customerId())
                .orElseThrow(() -> new BusinessException("고객 정보 없음"));
        Account newAccount = buildAccount(accountDto);

        customerDetail.addAccount(newAccount);

        customerDetailRepository.save(customerDetail);
    }

    @Override
    public CustomerDetailResponse getCustomerDetail(Long customerId) {
        CustomerDetail customerDetail = customerDetailRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("고객 정보 없음"));

        return CustomerDetailResponse.from(customerDetail);
    }

    private CustomerDetail buildCustomerDetail(CustomerDto customerDto) {
        return CustomerDetail.builder()
                .customerId(customerDto.customerId())
                .address(customerDto.address())
                .gender(customerDto.gender())
                .customerName(customerDto.customerName())
                .oneDayTransferLimit(customerDto.oneDayTransferLimit())
                .oneTimeTransferLimit(customerDto.oneTimeTransferLimit())
                .phoneNumber(customerDto.phoneNumber())
                .build();
    }

    private Account buildAccount(AccountDto accountDto) {
        return Account.builder()
                .accountName(accountDto.accountName())
                .accountBalance(accountDto.accountBalance())
                .accountNumber(accountDto.accountNumber())
                .newDateTime(accountDto.newDateTime())
                .build();
    }

}
