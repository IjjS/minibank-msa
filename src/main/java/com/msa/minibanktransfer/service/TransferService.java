package com.msa.minibanktransfer.service;

import com.msa.minibanktransfer.dto.response.TransferLimitResponse;

public interface TransferService {

    TransferLimitResponse createLimit(Long customerId);

    TransferLimitResponse retrieveTransferLimitOf(Long customerId);

}
