package com.msa.minibankaccount.service;

import com.msa.minibankaccount.domain.*;
import com.msa.minibankaccount.dto.request.CancelWithdrawalRequest;
import com.msa.minibankaccount.dto.request.TransferRequest;
import com.msa.minibankaccount.dto.response.TransactionResultResponse;
import com.msa.minibankaccount.exception.BusinessException;
import com.msa.minibankaccount.repository.AccountRepository;
import com.msa.minibankaccount.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Override
    @Transactional
    public TransactionResultResponse withdraw(TransferRequest request) {
        Account account = findAccount(request.accountNumber(), request.customerId());
        BigDecimal previousBalance = account.getAccountBalance();

        account.withdraw(request.transferAmount());

        TransactionHistory transactionHistory = saveHistory(account, request.transferBranch(), request.transferAmount(), DivisionCode.WITHDRAWAL);

        return TransactionResultResponse.from(transactionHistory, previousBalance);
    }

    @Override
    @Transactional
    public TransactionResultResponse cancelWithdrawal(Long accountNumber, Long sequence, CancelWithdrawalRequest request) {
        TransactionHistory transactionHistory = transactionHistoryRepository.findById(new AccountSequence(accountNumber, sequence))
                .orElseThrow(() -> new BusinessException("전산 기록 없음"));

        if (transactionHistory.isConfirmed()) {
            throw new BusinessException("이미 확정된 기록");
        }

        Account account = findAccount(accountNumber, request.customerId());
        BigDecimal previousBalance = account.getAccountBalance();

        account.addDeposit(request.transferAmount());
        transactionHistory.failTransfer();

        return TransactionResultResponse.from(transactionHistory, previousBalance);
    }

    @Override
    @Transactional
    public void confirmWithdrawal(Long accountNumber, Long sequence) {
        TransactionHistory transactionHistory = transactionHistoryRepository.findById(new AccountSequence(accountNumber, sequence))
                .orElseThrow(() -> new BusinessException("전산 기록 없음"));

        if (transactionHistory.isConfirmed()) {
            throw new BusinessException("이미 확정된 기록");
        }

        transactionHistory.confirm();
    }

    @Override
    @Transactional
    public TransactionResultResponse deposit(TransferRequest request) {
        Account account = findAccount(request.accountNumber(), request.customerId());
        BigDecimal previousBalance = account.getAccountBalance();

        account.addDeposit(request.transferAmount());

        TransactionHistory transactionHistory = saveHistory(account, request.transferBranch(), request.transferAmount(), DivisionCode.DEPOSIT);

        return TransactionResultResponse.from(transactionHistory, previousBalance);
    }

    private Account findAccount(Long accountNumber, Long customerId) {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new BusinessException("계좌 없음"));

        account.validateOwner(customerId);

        return account;
    }

    private TransactionHistory saveHistory(Account account, String transferBranch, BigDecimal transferAmount, DivisionCode divisionCode) {
        Long lastSequence = Objects.requireNonNullElse(transactionHistoryRepository.findLastSequenceByAccountNumber(account.getAccountNumber()), 0L);
        TransactionHistory history = TransactionHistory.builder()
                .accountNumber(account.getAccountNumber())
                .sequence(lastSequence + 1)
                .accountBalance(account.getAccountBalance())
                .statusCode(StatusCode.PENDING.getCode())
                .divisionCode(divisionCode.getCode())
                .transactionDateTime(LocalDateTime.now())
                .transferBranch(transferBranch)
                .transferAmount(transferAmount)
                .build();

        return transactionHistoryRepository.save(history);
    }

}
