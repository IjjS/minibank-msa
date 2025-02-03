package com.msa.minibanktransfer.service;

import com.msa.minibanktransfer.domain.TransferLimit;
import com.msa.minibanktransfer.dto.response.TransferLimitResponse;
import com.msa.minibanktransfer.exception.BusinessException;
import com.msa.minibanktransfer.repository.TransferLimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferLimitRepository transferLimitRepository;

    @Override
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

}
