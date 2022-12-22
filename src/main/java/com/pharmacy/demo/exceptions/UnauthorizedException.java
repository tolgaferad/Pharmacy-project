package com.pharmacy.demo.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg){
        super(msg);
    }
}
