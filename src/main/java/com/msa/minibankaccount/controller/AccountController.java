package com.msa.minibankaccount.controller;

import com.msa.minibankaccount.dto.AccountDto;
import com.msa.minibankaccount.dto.AccountNumberResponse;
import com.msa.minibankaccount.dto.RegisterAccountRequest;
import com.msa.minibankaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v0.8")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountNumberResponse> registerAccount(@RequestBody RegisterAccountRequest request) {
        AccountNumberResponse response = accountService.register(request);

        return ResponseEntity.created(null)
                .body(response);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<AccountDto>> retrieveAll(@PathVariable("customerId") Long customerId) {
        List<AccountDto> response = accountService.retrieveAllOfCustomer(customerId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{accountNumber}")
    public ResponseEntity<AccountDto> retrieveAccount(@PathVariable("accountNumber") Long accountNumber) {
        AccountDto response = accountService.retrieveOne(accountNumber);

        return ResponseEntity.ok(response);
    }

}
