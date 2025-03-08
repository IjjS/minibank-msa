package com.msa.minibankinquiry.service;

import com.msa.minibankinquiry.domain.CustomerDetail;
import com.msa.minibankinquiry.dto.CustomerDto;
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

}
