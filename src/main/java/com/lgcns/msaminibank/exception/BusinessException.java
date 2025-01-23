package com.lgcns.msaminibank.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
