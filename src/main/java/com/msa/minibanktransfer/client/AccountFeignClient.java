package com.msa.minibanktransfer.client;

import com.msa.minibanktransfer.dto.request.WithdrawalRequest;
import com.msa.minibanktransfer.dto.response.TransactionResult;
import com.msa.minibanktransfer.exception.SystemException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "minibank-account", fallbackFactory = AccountFeignFallbackFactory.class)
public interface AccountFeignClient {

    @PostMapping("/minibank/account/rest/v0.8/withdrawal")
    TransactionResult withdraw(WithdrawalRequest request);

}

@Component
@Slf4j
class AccountFeignFallbackFactory implements FallbackFactory<AccountFeignClient> {

    @Override
    public AccountFeignClient create(Throwable cause) {
        return new AccountFeignClient() {

            @Override
            public TransactionResult withdraw(WithdrawalRequest request) {
                throw new SystemException("타행 송금 실패: " + ((FeignException) cause).contentUTF8());
            }

        };
    }

}
