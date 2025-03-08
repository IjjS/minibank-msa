package com.msa.minibanktransfer.dto.response;

import com.msa.minibanktransfer.domain.TransferLimit;

import java.math.BigDecimal;

public record TransferLimitResponse(BigDecimal oneTimeTransferLimit, BigDecimal oneDayTransferLimit) {

    public static TransferLimitResponse from(TransferLimit transferLimit) {
        return new TransferLimitResponse(transferLimit.getOneTimeTransferLimit(), transferLimit.getOneDayTransferLimit());
    }

}
