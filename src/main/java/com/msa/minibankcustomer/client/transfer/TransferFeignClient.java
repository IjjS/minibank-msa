package com.msa.minibankcustomer.client.transfer;

import com.msa.minibankcustomer.dto.TransferLimitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("minibank-transfer")
public interface TransferFeignClient {

    @PostMapping("/minibank/transfer/rest/v0.8/{customerId}")
    TransferLimitDto createFirstLimit(@PathVariable("customerId") Long customerId);

    @GetMapping("/minibank/transfer/rest/v0.8/{customerId}/limit")
    TransferLimitDto retrieveTransferLimit(@PathVariable("customerId") Long customerId);

}
