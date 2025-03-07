package com.msa.minibankaccount.client.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("minibank-customer")
public interface CustomerFeignClient {

    @GetMapping("/minibank/customer/rest/v0.8/{customerId}")
    CustomerDto retrieveCustomer(@PathVariable("customerId") Long customerId);

}
