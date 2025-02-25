package com.msa.minibanktransfer.service;

import com.msa.minibanktransfer.client.AccountFeignClient;
import com.msa.minibanktransfer.domain.*;
import com.msa.minibanktransfer.dto.request.BankToBankTransferRequest;
import com.msa.minibanktransfer.dto.request.CancelWithdrawalRequest;
import com.msa.minibanktransfer.dto.request.TransferRequest;
import com.msa.minibanktransfer.dto.request.WithdrawalRequest;
import com.msa.minibanktransfer.dto.response.BankToBankTransferResult;
import com.msa.minibanktransfer.dto.response.TransactionResult;
import com.msa.minibanktransfer.dto.response.TransferHistoryIdResponse;
import com.msa.minibanktransfer.dto.response.TransferLimitResponse;
import com.msa.minibanktransfer.exception.BusinessException;
import com.msa.minibanktransfer.publisher.TransferProducer;
import com.msa.minibanktransfer.repository.TransferHistoryRepository;
import com.msa.minibanktransfer.repository.TransferLimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferHistoryRepository transferHistoryRepository;
    private final TransferLimitRepository transferLimitRepository;
    private final AccountFeignClient accountFeignClient;
    private final TransferProducer transferProducer;

    @Override
    @Transactional
    public TransferHistoryIdResponse transferBankToBank(TransferRequest request) {
        TransferHistory transferHistory = saveTransferHistory(request, DivisionCode.OUTWARD);
        WithdrawalRequest withdrawalRequest = WithdrawalRequest.builder()
                .accountNumber(request.withdrawalAccountNumber())
                .transferAmount(request.transferAmount())
                .customerId(request.customerId())
                .transferBranch(request.senderMemo())
                .isFailed(request.isFailed())
                .build();
        TransactionResult transactionResult = accountFeignClient.withdraw(withdrawalRequest);
        BankToBankTransferRequest bankToBankRequest = BankToBankTransferRequest.from(request, transferHistory.getSequence(), transactionResult.sequence());

        transferProducer.sendBToBTransfer(bankToBankRequest);

        return new TransferHistoryIdResponse(transferHistory.getCustomerId(), transferHistory.getSequence());
    }

    @Override
    @Transactional
    public void applyTransferResult(BankToBankTransferResult transferResult) {
        TransferHistory transferHistory = transferHistoryRepository.findById(new CustomerSequence(transferResult.customerId(), transferResult.customerSequence()))
                .orElseThrow(() -> new BusinessException("이체 기록 없음"));

        if (transferResult.isFailed()) {
            CancelWithdrawalRequest request = new CancelWithdrawalRequest(transferResult.customerId(), transferResult.transferAmount());

            accountFeignClient.cancelWithdrawal(transferResult.withdrawalAccountNumber(), transferResult.accountSequence(), request);
            transferHistory.fail();

            return;
        }

        accountFeignClient.confirmWithdrawal(transferResult.withdrawalAccountNumber(), transferResult.accountSequence());
        transferHistory.succeed();
    }

    @Override
    @Transactional
    public TransferLimitResponse createLimit(Long customerId) {
        if (transferLimitRepository.existsById(customerId)) {
            throw new BusinessException("이체 제한 정보 존재");
        }

        TransferLimit transferLimit = TransferLimit.createFirstLimit(customerId);
        TransferLimit saved = transferLimitRepository.save(transferLimit);

        return TransferLimitResponse.from(saved);
    }

    @Override
    public TransferLimitResponse retrieveTransferLimitOf(Long customerId) {
        TransferLimit transferLimit = transferLimitRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("고객 없음"));

        return TransferLimitResponse.from(transferLimit);
    }

    private TransferHistory saveTransferHistory(TransferRequest request, DivisionCode divisionCode) {
        Long lastSequence = Objects.requireNonNullElse(transferHistoryRepository.findLastSequenceByCustomerId(request.customerId()), 0L);
        TransferHistory transferHistory = TransferHistory.builder()
                .withdrawalAccountNumber(request.withdrawalAccountNumber())
                .depositAccountNumber(request.depositAccountNumber())
                .receiverCustomerName(request.receiverCustomerName())
                .transferAmount(request.transferAmount())
                .transferDateTime(LocalDateTime.now())
                .receiverMemo(request.receiverMemo())
                .senderMemo(request.senderMemo())
                .customerId(request.customerId())
                .sequence(lastSequence + 1)
                .divisionCode(divisionCode)
                .statusCode(StatusCode.CREATED)
                .build();

        return transferHistoryRepository.save(transferHistory);
    }

}
