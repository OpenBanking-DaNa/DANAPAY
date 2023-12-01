package com.dana.danapay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
public class TokenException extends RuntimeException {

    public TokenException(String msg) {
        super(msg);
    }
}