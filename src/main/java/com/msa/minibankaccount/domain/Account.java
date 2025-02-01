package com.msa.minibankaccount.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Account {

    @Id
    private Long accountNumber;
    private String accountName;
    private Long customerId;
    private String customerName;
    private BigDecimal accountBalance;
    private LocalDateTime newDateTime;

    public void addDeposit(BigDecimal transferAmount) {
        this.accountBalance = this.accountBalance.add(transferAmount);
    }

    public boolean withdraw(BigDecimal transferAmount) {
        BigDecimal withdrawn = this.accountBalance.subtract(transferAmount);

        if (withdrawn.compareTo(BigDecimal.ZERO) > 0) {
            this.accountBalance = withdrawn;
        }

        return this.accountBalance.equals(withdrawn);
    }

}
