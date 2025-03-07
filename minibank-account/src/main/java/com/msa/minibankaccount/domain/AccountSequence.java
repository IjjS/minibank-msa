package com.msa.minibankaccount.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountSequence implements Serializable {

    private Long accountNumber;
    private Long sequence;

}
