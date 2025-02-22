package com.msa.minibanktransfer.service;

import com.msa.minibanktransfer.dto.request.TransferRequest;
import com.msa.minibanktransfer.dto.response.TransferHistoryIdResponse;
import com.msa.minibanktransfer.dto.response.TransferLimitResponse;

public interface TransferService {

    TransferHistoryIdResponse transferBankToBank(TransferRequest request);

    TransferLimitResponse createLimit(Long customerId);

    TransferLimitResponse retrieveTransferLimitOf(Long customerId);

}
