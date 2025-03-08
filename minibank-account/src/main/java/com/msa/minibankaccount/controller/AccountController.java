package com.msa.minibankaccount.controller;

import com.msa.minibankaccount.dto.request.CancelWithdrawalRequest;
import com.msa.minibankaccount.dto.request.RegisterAccountRequest;
import com.msa.minibankaccount.dto.request.TransferRequest;
import com.msa.minibankaccount.dto.response.AccountNumberResponse;
import com.msa.minibankaccount.dto.response.AccountResponse;
import com.msa.minibankaccount.dto.response.TransactionResultResponse;
import com.msa.minibankaccount.service.AccountService;
import com.msa.minibankaccount.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v0.8")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<AccountNumberResponse> registerAccount(@RequestBody RegisterAccountRequest request) {
        AccountNumberResponse response = accountService.register(request);

        return ResponseEntity.created(null)
                .body(response);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<AccountResponse>> retrieveAll(@PathVariable("customerId") Long customerId) {
        List<AccountResponse> response = accountService.retrieveAllOfCustomer(customerId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{accountNumber}")
    public ResponseEntity<AccountResponse> retrieveAccount(@PathVariable("accountNumber") Long accountNumber) {
        AccountResponse response = accountService.retrieveOne(accountNumber);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResultResponse> deposit(@RequestBody TransferRequest request) {
        TransactionResultResponse response = transactionService.deposit(request);

        return ResponseEntity.created(null)
                .body(response);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TransactionResultResponse> withdraw(@RequestBody TransferRequest request) {
        TransactionResultResponse response = transactionService.withdraw(request);

        return ResponseEntity.created(null)
                .body(response);
    }

    @PatchMapping("/withdrawal/{accountNumber}/{sequence}")
    public ResponseEntity<Void> confirmTransaction(@PathVariable("accountNumber") Long accountNumber, @PathVariable("sequence") Long sequence) {
        transactionService.confirmWithdrawal(accountNumber, sequence);

        return ResponseEntity.noContent()
                .build();
    }

    @PatchMapping("/withdrawal/{accountNumber}/{sequence}/cancel")
    public ResponseEntity<TransactionResultResponse> cancelWithdrawal(@PathVariable("accountNumber") Long accountNumber, @PathVariable("sequence") Long sequence, @RequestBody CancelWithdrawalRequest request) {
        TransactionResultResponse response = transactionService.cancelWithdrawal(accountNumber, sequence, request);

        return ResponseEntity.ok()
                .body(response);
    }

}
