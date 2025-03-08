package com.msa.minibanktransfer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum StatusCode {
    CREATED("0001"),
    SUCCESSFUL("0002"),
    FAILED("0003");

    private final String code;

    public static StatusCode get(String code) {
        return Arrays.stream(StatusCode.values())
                .filter(statusCode -> statusCode.code.equals(code))
                .findFirst()
                .orElseThrow();
    }
}
