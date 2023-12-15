package com.example.btl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartAlreadyExitsException extends RuntimeException {
    public CartAlreadyExitsException(String message){
        super(message);
    }
}
