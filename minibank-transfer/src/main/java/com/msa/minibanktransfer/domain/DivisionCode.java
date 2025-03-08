package com.msa.minibanktransfer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum DivisionCode {
    INWARD("0001"),
    OUTWARD("0002");

    private final String code;

    public static DivisionCode get(String code) {
        return Arrays.stream(DivisionCode.values())
                .filter(divisionCode -> divisionCode.code.equals(code))
                .findFirst()
                .orElseThrow();
    }


}
