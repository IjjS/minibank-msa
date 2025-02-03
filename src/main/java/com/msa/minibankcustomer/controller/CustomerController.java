package com.msa.minibankcustomer.controller;

import com.msa.minibankcustomer.dto.request.CreateCustomerRequest;
import com.msa.minibankcustomer.dto.request.UpdateCustomerRequest;
import com.msa.minibankcustomer.dto.response.CustomerDetailResponse;
import com.msa.minibankcustomer.dto.response.IdResponse;
import com.msa.minibankcustomer.dto.response.SimpleCustomerResponse;
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
    public ResponseEntity<IdResponse> createCustomer(@RequestBody CreateCustomerRequest request) {
        IdResponse response = customerService.createCustomer(request);

        return ResponseEntity.created(null)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<SimpleCustomerResponse>> retrieveCustomers() {
        List<SimpleCustomerResponse> response = customerService.retrieveCustomers();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleCustomerResponse> retrieveCustomer(@PathVariable("id") Long id) {
        SimpleCustomerResponse response = customerService.retrieveCustomer(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<CustomerDetailResponse> retrieveCustomerDetail(@PathVariable("id") Long id) {
        CustomerDetailResponse response = customerService.retrieveCustomerDetail(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleCustomerResponse> updateCustomer(@PathVariable("id") Long id, @RequestBody UpdateCustomerRequest request) {
        SimpleCustomerResponse response = customerService.updateCustomer(id, request);

        return ResponseEntity.ok(response);
    }

}
