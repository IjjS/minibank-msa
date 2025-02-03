package com.msa.minibankaccount.client.customer;

import com.msa.minibankaccount.config.RestClientProperties;
import com.msa.minibankaccount.exception.BusinessException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerRestClient {

    private static final String pathV8 = "/rest/v0.8";
    private static final String idPathVariable = "/{customerId}";

    private final RestTemplate restTemplate;
    private final RestClientProperties restClientProperties;

    @CircuitBreaker(name = "customer", fallbackMethod = "fallbackRetrieveCustomer")
    public CustomerDto retrieveCustomer(Long customerId) {
        String customerUrl = restClientProperties.getCustomerUrl() + pathV8 + idPathVariable;

        return restTemplate.getForObject(customerUrl, CustomerDto.class, customerId);
    }

    public CustomerDto fallbackRetrieveCustomer(Long customerId, Throwable t) {
        log.error("Circuit Breaker Fallback", t);

        throw new BusinessException("호출 실패");
    }

}
