package com.lgcns.msaminibank.service;

import com.lgcns.msaminibank.domain.Customer;
import com.lgcns.msaminibank.dto.CustomerDto;
import com.lgcns.msaminibank.exception.BusinessException;
import com.lgcns.msaminibank.repository.CustomerRepository;
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
    public Integer createCustomer(CustomerDto request) {
        if (customerRepository.existsById(request.cstmId())) {
            throw new BusinessException("아이디 중복");
        }

        Customer customer = new Customer(request.cstmId(), request.cstmNm(), request.cstmAge(), request.cstmGnd(), request.cstmPn(), request.cstmAdr());
        Customer saved = customerRepository.save(customer);

        return saved.getCstmId();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto retrieveCustomer(Integer id) {
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
    public CustomerDto updateCustomer(Integer cstmId, CustomerDto update) {
        Customer customer = findOrThrow(cstmId);

        if (!Objects.equals(customer.getCstmId(), update.cstmId())) {
            throw new BusinessException("아이디 수정 불가");
        }

        customer.update(update.cstmId(), update.cstmNm(), update.cstmAge(), update.cstmGnd(), update.cstmPn(), update.cstmAdr());
        customerRepository.save(customer);

        return CustomerDto.from(customer);
    }

    @Override
    public Boolean existsCustomer(Integer id) {
        return null;
    }

    private Customer findOrThrow(Integer cstmId) {
        return customerRepository.findById(cstmId)
                .orElseThrow(() -> new BusinessException("고객 없음"));
    }
}
