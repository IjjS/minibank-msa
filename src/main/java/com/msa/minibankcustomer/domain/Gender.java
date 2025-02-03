package com.msa.minibankcustomer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String code;

    public static Gender get(String code) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.code.equals(code))
                .findFirst()
                .orElseThrow();
    }

}
