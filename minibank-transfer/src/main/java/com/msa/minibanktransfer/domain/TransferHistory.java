package com.msa.minibanktransfer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transfer_histories")
@NoArgsConstructor
@Getter
public class TransferHistory {

    @EmbeddedId
    @Getter(AccessLevel.NONE)
    private CustomerSequence customerSequence;
    private Long depositAccountNumber;
    private Long withdrawalAccountNumber;
    private String receiverMemo;
    private String senderMemo;
    private String receiverCustomerName;
    private BigDecimal transferAmount;
    private LocalDateTime transferDateTime;

    @Builder
    private TransferHistory(Long customerId, Long sequence, Long depositAccountNumber, Long withdrawalAccountNumber, String receiverMemo, String senderMemo, String receiverCustomerName, BigDecimal transferAmount, LocalDateTime transferDateTime, DivisionCode divisionCode, StatusCode statusCode) {
        this.customerSequence = new CustomerSequence(customerId, sequence);
        this.depositAccountNumber = depositAccountNumber;
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.receiverMemo = receiverMemo;
        this.senderMemo = senderMemo;
        this.receiverCustomerName = receiverCustomerName;
        this.transferAmount = transferAmount;
        this.transferDateTime = transferDateTime;
        this.divisionCode = divisionCode;
        this.statusCode = statusCode;
    }

    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private DivisionCode divisionCode;

    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;

    public Long getCustomerId() {
        return customerSequence.getCustomerId();
    }

    public Long getSequence() {
        return customerSequence.getSequence();
    }

    public void fail() {
        this.statusCode = StatusCode.FAILED;
    }

    public void succeed() {
        this.statusCode = StatusCode.SUCCESSFUL;
    }

}
