package com.msa.minibankaccount.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_transaction_histories")
@NoArgsConstructor
@Getter
public class TransactionHistory {

    @EmbeddedId
    @Getter(AccessLevel.NONE)
    private AccountSequence accountSequence;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private DivisionCode divisionCode;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private StatusCode statusCode;
    private BigDecimal transferAmount;
    private BigDecimal accountBalance;
    private String transferBranch;
    private LocalDateTime transactionDateTime;

    @Builder
    private TransactionHistory(Long accountNumber, Long sequence, String divisionCode, String statusCode, BigDecimal transferAmount, BigDecimal accountBalance, String transferBranch, LocalDateTime transactionDateTime) {
        this.accountSequence = new AccountSequence(accountNumber, sequence);
        this.divisionCode = DivisionCode.get(divisionCode);
        this.statusCode = StatusCode.get(statusCode);
        this.transferAmount = transferAmount;
        this.accountBalance = accountBalance;
        this.transferBranch = transferBranch;
        this.transactionDateTime = transactionDateTime;
    }

    public Long getAccountNumber() {
        return accountSequence.getAccountNumber();
    }

    public Long getSequence() {
        return accountSequence.getSequence();
    }

    public void updateBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        this.statusCode = StatusCode.SUCCESSFUL;
    }

    public void failTransfer(BigDecimal appendBackAmount) {
        if (Objects.nonNull(appendBackAmount)) {
            this.accountBalance = this.accountBalance.add(appendBackAmount);
        }

        this.statusCode = StatusCode.FAILED;
    }

    public void confirm() {
        this.statusCode = StatusCode.SUCCESSFUL;
    }

    public boolean isConfirmed() {
        return this.statusCode != StatusCode.PENDING;
    }

}
