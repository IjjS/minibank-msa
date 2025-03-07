package com.msa.minibankaccount.service;

import com.msa.minibankaccount.dto.request.CancelWithdrawalRequest;
import com.msa.minibankaccount.dto.request.TransferRequest;
import com.msa.minibankaccount.dto.response.TransactionResultResponse;

public interface TransactionService {

    TransactionResultResponse withdraw(TransferRequest request);

    TransactionResultResponse cancelWithdrawal(Long accountNumber, Long sequence, CancelWithdrawalRequest request);

    void confirmWithdrawal(Long accountNumber, Long sequence);

    TransactionResultResponse deposit(TransferRequest request);

}
