package com.msa.minibanktransfer.client;

import com.msa.minibanktransfer.dto.request.CancelWithdrawalRequest;
import com.msa.minibanktransfer.dto.request.WithdrawalRequest;
import com.msa.minibanktransfer.dto.response.TransactionResult;
import com.msa.minibanktransfer.exception.SystemException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "minibank-account", fallbackFactory = AccountFeignFallbackFactory.class)
public interface AccountFeignClient {

    @PostMapping("/minibank/account/rest/v0.8/withdrawal")
    TransactionResult withdraw(WithdrawalRequest request);

    @PatchMapping("/minibank/account/rest/v0.8/withdrawal/{accountNumber}/{sequence}")
    void confirmWithdrawal(@PathVariable("accountNumber") Long accountNumber, @PathVariable("sequence") Long sequence);

    @PatchMapping("/minibank/account/rest/v0.8/withdrawal/{accountNumber}/{sequence}/cancel")
    TransactionResult cancelWithdrawal(@PathVariable("accountNumber") Long accountNumber, @PathVariable("sequence") Long sequence, @RequestBody CancelWithdrawalRequest request);

}

@Component
@Slf4j
class AccountFeignFallbackFactory implements FallbackFactory<AccountFeignClient> {

    @Override
    public AccountFeignClient create(Throwable cause) {
        String message = getMessage(cause);

        return new AccountFeignClient() {

            @Override
            public TransactionResult withdraw(WithdrawalRequest request) {
                throw new SystemException("타행이체 실패: " + message);
            }

            @Override
            public void confirmWithdrawal(Long accountNumber, Long sequence) {
                throw new SystemException("이체 확정 실패: " + message);
            }

            @Override
            public TransactionResult cancelWithdrawal(Long accountNumber, Long sequence, CancelWithdrawalRequest request) {
                throw new SystemException("이체 취소 실패: " + message);
            }

        };
    }

    private String getMessage(Throwable cause) {
        if (cause instanceof FeignException exception) {
            String message = exception.contentUTF8();

            if (StringUtils.isBlank(message)) {
                return exception.getMessage();
            }

            return message;
        }

        return cause.getMessage();
    }

}
