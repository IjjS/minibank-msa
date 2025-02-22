package com.msa.minibankaccount.domain;

import com.msa.minibankaccount.exception.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum DivisionCode {
    DEPOSIT("0001"),
    WITHDRAWAL("0002");

    private final String code;

    public static DivisionCode get(String code) {
        return Arrays.stream(DivisionCode.values())
                .filter(division -> division.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new BusinessException("DivisionCode 예외"));
    }

}
