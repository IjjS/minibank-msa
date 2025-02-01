package com.msa.minibankaccount.controller;

import com.msa.minibankaccount.dto.AccountDto;
import com.msa.minibankaccount.dto.AccountNumberResponse;
import com.msa.minibankaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v0.8/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountNumberResponse> registerAccount(@RequestBody AccountDto request) {
        AccountNumberResponse response = accountService.register(request);

        return ResponseEntity.created(null)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> retrieveAll() {
        List<AccountDto> response = accountService.retrieveAll();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> retrieveAccount(@PathVariable("accountNumber") Long accountNumber) {
        AccountDto response = accountService.retrieveOne(accountNumber);

        return ResponseEntity.ok(response);
    }

}
