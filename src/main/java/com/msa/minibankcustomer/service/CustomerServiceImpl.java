package com.msa.minibankcustomer.service;

import com.msa.minibankcustomer.domain.Customer;
import com.msa.minibankcustomer.dto.CustomerDto;
import com.msa.minibankcustomer.dto.IdResponse;
import com.msa.minibankcustomer.exception.BusinessException;
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

    @Override
    @Transactional
    public IdResponse createCustomer(CustomerDto request) {
        if (customerRepository.existsById(request.id())) {
            throw new BusinessException("아이디 중복");
        }

        Customer customer = new Customer(request.id(), request.name(), request.age(), request.gender(), request.phoneNumber(), request.address());
        Customer saved = customerRepository.save(customer);

        return new IdResponse(saved.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto retrieveCustomer(Long id) {
        Customer customer = findOrThrow(id);

        return CustomerDto.from(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> retrieveCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerDto::from)
                .toList();
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto update) {
        Customer customer = findOrThrow(id);

        if (!Objects.equals(customer.getId(), update.id())) {
            throw new BusinessException("아이디 수정 불가");
        }

        customer.update(update.id(), update.name(), update.age(), update.gender(), update.phoneNumber(), update.address());
        customerRepository.save(customer);

        return CustomerDto.from(customer);
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
