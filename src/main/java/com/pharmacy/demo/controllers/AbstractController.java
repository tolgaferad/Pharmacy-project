package com.pharmacy.demo.controllers;

import com.pharmacy.demo.exceptions.AuthenticationException;
import com.pharmacy.demo.exceptions.BadRequestException;
import com.pharmacy.demo.exceptions.NotFoundException;
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

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequest(BadRequestException e) {
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(NotFoundException e) {
        return new ErrorDTO(e.getMessage());
    }
}
