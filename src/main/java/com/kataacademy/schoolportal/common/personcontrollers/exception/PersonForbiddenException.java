package com.kataacademy.schoolportal.common.personcontrollers.exception;

public class PersonForbiddenException extends RuntimeException{
    public PersonForbiddenException(String message) {
        super("Доступ запрещен. Выполните аутентификацию." + message);
    }
}
