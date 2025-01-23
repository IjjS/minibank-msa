package com.lgcns.msaminibank.service;

import com.lgcns.msaminibank.domain.Customer;
import com.lgcns.msaminibank.dto.CustomerDto;
import com.lgcns.msaminibank.exception.BusinessException;
import com.lgcns.msaminibank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Integer createCustomer(CustomerDto request) {
        boolean existing = customerRepository.existsById(request.cstmId());

        if (existing) {
            throw new BusinessException("아이디 중복");
        }

        Customer customer = new Customer(request.cstmId(), request.cstmNm(), request.cstmAge(), request.cstmGnd(), request.cstmPn(), request.cstmAdr());
        Customer saved = customerRepository.save(customer);

        return saved.getCstmId();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto retrieveCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("고객 없음"));

        return CustomerDto.from(customer);
    }

    @Override
    public Boolean existsCustomer(Integer id) {
        return null;
    }
}
