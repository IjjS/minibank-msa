package com.msa.minibankcustomer.service;

import com.msa.minibankcustomer.client.account.AccountFeignClient;
import com.msa.minibankcustomer.client.transfer.TransferFeignClient;
import com.msa.minibankcustomer.domain.Customer;
import com.msa.minibankcustomer.dto.AccountDto;
import com.msa.minibankcustomer.dto.CustomerInquiryDto;
import com.msa.minibankcustomer.dto.TransferLimitDto;
import com.msa.minibankcustomer.dto.request.CreateCustomerRequest;
import com.msa.minibankcustomer.dto.request.UpdateCustomerRequest;
import com.msa.minibankcustomer.dto.response.CustomerDetailResponse;
import com.msa.minibankcustomer.dto.response.IdResponse;
import com.msa.minibankcustomer.dto.response.SimpleCustomerResponse;
import com.msa.minibankcustomer.exception.BusinessException;
import com.msa.minibankcustomer.publisher.CustomerInquiryProducer;
import com.msa.minibankcustomer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountFeignClient accountFeignClient;
    private final TransferFeignClient transferFeignClient;
    private final CustomerInquiryProducer customerInquiryProducer;

    @Override
    @Transactional
    public IdResponse createCustomer(CreateCustomerRequest request) {
        Customer customer = Customer.builder()
                .name(request.name())
                .age(request.age())
                .gender(request.gender())
                .address(request.address())
                .phoneNumber(request.phoneNumber())
                .build();
        Customer saved = customerRepository.save(customer);
        TransferLimitDto limits = transferFeignClient.createFirstLimit(saved.getId());
        CustomerInquiryDto customerInquiry = CustomerInquiryDto.from(saved, limits);

        customerInquiryProducer.sendCreatedCustomer(customerInquiry);

        return new IdResponse(saved.getId());
    }

    @Override
    public SimpleCustomerResponse retrieveCustomer(Long id) {
        Customer customer = findOrThrow(id);

        return SimpleCustomerResponse.from(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDetailResponse retrieveCustomerDetail(Long id) {
        Customer customer = findOrThrow(id);
        List<AccountDto> accounts = accountFeignClient.retrieveAccounts(customer.getId());
        TransferLimitDto transferLimit = transferFeignClient.retrieveTransferLimit(customer.getId());

        return CustomerDetailResponse.from(customer, accounts, transferLimit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleCustomerResponse> retrieveCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(SimpleCustomerResponse::from)
                .toList();
    }

    @Override
    public SimpleCustomerResponse updateCustomer(Long id, UpdateCustomerRequest update) {
        Customer customer = findOrThrow(id);

        if (!Objects.equals(customer.getId(), update.id())) {
            throw new BusinessException("수정 불가");
        }

        customer.update(update.id(), update.name(), update.age(), update.gender(), update.phoneNumber(), update.address());
        customerRepository.save(customer);

        return SimpleCustomerResponse.from(customer);
    }

    @Override
    public Boolean existsCustomer(Long id) {
        return null;
    }

    private Customer findOrThrow(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("고객 없음"));
    }
}
