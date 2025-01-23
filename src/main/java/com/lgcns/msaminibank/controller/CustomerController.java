package com.lgcns.msaminibank.controller;

import com.lgcns.msaminibank.dto.CustomerDto;
import com.lgcns.msaminibank.dto.IdResponse;
import com.lgcns.msaminibank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v0.8")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<IdResponse> createCustomer(@RequestBody CustomerDto request) {
        Integer id = customerService.createCustomer(request);

        return ResponseEntity.created(null)
                .body(new IdResponse(id));
    }

    @GetMapping("/{cstmId}")
    public ResponseEntity<CustomerDto> retrieveCustomer(@PathVariable("cstmId") Integer cstmId) {
        CustomerDto response = customerService.retrieveCustomer(cstmId);

        return ResponseEntity.ok(response);
    }

}
