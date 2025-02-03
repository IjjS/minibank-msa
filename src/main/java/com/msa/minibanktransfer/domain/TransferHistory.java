package com.msa.minibanktransfer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transfer_histories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransferHistory {

    @EmbeddedId
    private CustomerSequence customerSequence;
    private Long depositAccountNumber;
    private Long withdrawalAccountNumber;
    private String receiverMemo;
    private String senderMemo;
    private String receiverCustomerName;
    private BigDecimal transferAmount;
    private LocalDateTime transferDateTime;

    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    @Getter(AccessLevel.NONE)
    private DivisionCode divisionCode;

    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    @Getter(AccessLevel.NONE)
    private StatusCode statusCode;

    public String getDivisionCode() {
        return divisionCode.getCode();
    }

    public String getStatusCode() {
        return statusCode.getCode();
    }

}
