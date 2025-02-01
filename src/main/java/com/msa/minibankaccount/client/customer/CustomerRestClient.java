package com.msa.minibankaccount.client.customer;

import com.msa.minibankaccount.client.RestClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CustomerRestClient {

    private static final String pathV8 = "/rest/v0.8";
    private static final String idPathVariable = "/{customerId}";

    private final RestTemplate restTemplate;
    private final RestClientProperties restClientProperties;

    public CustomerDto retrieveCustomer(Long customerId) {
        String customerUrl = restClientProperties.getCustomerUrl() + pathV8 + idPathVariable;

        return restTemplate.getForObject(customerUrl, CustomerDto.class, customerId);
    }

}
