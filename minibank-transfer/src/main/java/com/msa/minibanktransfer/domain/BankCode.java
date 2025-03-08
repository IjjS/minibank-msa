package com.msa.minibanktransfer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public enum BankCode {
    A_BANK("001"),
    B_BANK("002");

    private final String code;

    public static BankCode get(String code) {
        return Arrays.stream(BankCode.values())
                .filter(bankCode -> Objects.equals(code, bankCode.code))
                .findFirst()
                .orElseThrow();
    }
}
