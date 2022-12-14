package com.pharmacy.demo.controllers;

import com.pharmacy.demo.exceptions.AuthenticationException;
import com.pharmacy.demo.models.dto.errorDTO.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbstractController {
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleUnauthorized(AuthenticationException e) {
        return new ErrorDTO(e.getMessage());
    }

}
