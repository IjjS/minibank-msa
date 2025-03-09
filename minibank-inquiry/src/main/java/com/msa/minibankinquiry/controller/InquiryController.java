package com.msa.minibankinquiry.controller;

import com.msa.minibankinquiry.dto.response.CustomerDetailResponse;
import com.msa.minibankinquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping("/rest/v0.8/{customerId}")
    public ResponseEntity<CustomerDetailResponse> getCustomerDetail(@PathVariable("customerId") Long customerId) {
        CustomerDetailResponse response = inquiryService.getCustomerDetail(customerId);

        return ResponseEntity.ok(response);
    }

}
