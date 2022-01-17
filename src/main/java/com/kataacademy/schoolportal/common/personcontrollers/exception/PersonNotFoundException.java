package com.kataacademy.schoolportal.common.personcontrollers.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String name, Long id) {
        super("ERROR: Не найден " + name + " с id=" + id);
    }
}
