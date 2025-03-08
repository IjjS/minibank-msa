package com.msa.minibanktransfer.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSequence {

    private Long customerId;
    private Long sequence;

}
