package com.kataacademy.schoolportal.common.personcontrollers.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
