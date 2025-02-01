package com.msa.minibankcustomer.controller;

import com.msa.minibankcustomer.dto.CustomerDto;
import com.msa.minibankcustomer.dto.IdResponse;
import com.msa.minibankcustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v0.8")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<IdResponse> createCustomer(@RequestBody CustomerDto request) {
        IdResponse response = customerService.createCustomer(request);

        return ResponseEntity.created(null)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> retrieveCustomers() {
        List<CustomerDto> response = customerService.retrieveCustomers();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> retrieveCustomer(@PathVariable("id") Long id) {
        CustomerDto response = customerService.retrieveCustomer(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto request) {
        CustomerDto response = customerService.updateCustomer(id, request);

        return ResponseEntity.ok(response);
    }

}
