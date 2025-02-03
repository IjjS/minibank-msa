package com.msa.minibanktransfer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_transfer_limits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransferLimit {

    private static final BigDecimal DEFAULT_ONE_TIME_LIMIT = BigDecimal.valueOf(5_000_000);
    private static final BigDecimal DEFAULT_ONE_DAY_LIMIT = BigDecimal.valueOf(50_000_000);

    @Id
    private Long customerId;
    private BigDecimal oneDayTransferLimit;
    private BigDecimal oneTimeTransferLimit;

    public static TransferLimit createFirstLimit(Long customerId) {
        return new TransferLimit(customerId, DEFAULT_ONE_DAY_LIMIT, DEFAULT_ONE_TIME_LIMIT);
    }

}
