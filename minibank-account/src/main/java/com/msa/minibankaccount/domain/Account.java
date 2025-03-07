package com.msa.minibankaccount.domain;

import com.msa.minibankaccount.exception.BusinessException;
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

    public void validateOwner(Long customerId) {
        if (this.customerId.equals(customerId)) {
            return;
        }

        throw new BusinessException("소유자 불일치");
    }

    public void addDeposit(BigDecimal transferAmount) {
        this.accountBalance = this.accountBalance.add(transferAmount);
    }

    public void withdraw(BigDecimal transferAmount) {
        BigDecimal withdrawn = this.accountBalance.subtract(transferAmount);

        if (withdrawn.signum() == -1) {
            throw new BusinessException("잔액 부족");
        }

        this.accountBalance = withdrawn;
    }

}
