package com.msa.minibanktransfer.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class CustomerSequence {

    private Long customerId;
    private Long sequence;

}
