package com.msa.minibankaccount.domain;

import com.msa.minibankaccount.exception.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum StatusCode {
    PENDING("0001"),
    SUCCESSFUL("0002"),
    FAILED("0003");

    private final String code;

    public static StatusCode get(String code) {
        return Arrays.stream(StatusCode.values())
                .filter(status -> status.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new BusinessException("StatusCode 예외"));
    }

}
