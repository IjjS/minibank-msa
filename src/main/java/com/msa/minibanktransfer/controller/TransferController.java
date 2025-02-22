package com.msa.minibanktransfer.controller;

import com.msa.minibanktransfer.dto.request.TransferRequest;
import com.msa.minibanktransfer.dto.response.TransferHistoryIdResponse;
import com.msa.minibanktransfer.dto.response.TransferLimitResponse;
import com.msa.minibanktransfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/v0.8")
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/btob")
    public ResponseEntity<TransferHistoryIdResponse> transferBankToBank(@RequestBody TransferRequest request) {
        TransferHistoryIdResponse response = transferService.transferBankToBank(request);

        return ResponseEntity.created(null)
                .body(response);
    }

    @PostMapping("/limit/{customerId}")
    public ResponseEntity<TransferLimitResponse> createLimit(@PathVariable("customerId") Long customerId) {
        TransferLimitResponse response = transferService.createLimit(customerId);

        return ResponseEntity.created(null)
                .body(response);
    }

    @GetMapping("/limit/{customerId}")
    public ResponseEntity<TransferLimitResponse> retrieveTransferLimitOf(@PathVariable("customerId") Long customerId) {
        TransferLimitResponse response = transferService.retrieveTransferLimitOf(customerId);

        return ResponseEntity.ok(response);
    }

}
