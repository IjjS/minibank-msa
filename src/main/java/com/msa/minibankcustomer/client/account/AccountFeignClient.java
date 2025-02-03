package com.msa.minibankcustomer.client.account;

import com.msa.minibankcustomer.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("minibank-account")
public interface AccountFeignClient {

    @GetMapping("/minibank/account/rest/v0.8/customers/{customerId}")
    List<AccountDto> retrieveAccounts(@PathVariable("customerId") Long customerId);

}
