package com.msa.minibankaccount.service;

import com.msa.minibankaccount.dto.request.TransferRequest;
import com.msa.minibankaccount.dto.response.TransactionResultResponse;

public interface TransactionService {

    TransactionResultResponse withdraw(TransferRequest request);

    TransactionResultResponse deposit(TransferRequest request);

}
