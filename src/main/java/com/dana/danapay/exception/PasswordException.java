package com.dana.danapay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
public class PasswordException extends RuntimeException {

    public PasswordException(String msg){
        super(msg);
    }


}
