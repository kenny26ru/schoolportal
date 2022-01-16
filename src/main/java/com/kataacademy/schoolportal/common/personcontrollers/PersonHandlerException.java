package com.kataacademy.schoolportal.common.personcontrollers;

import com.kataacademy.schoolportal.common.personcontrollers.exception.BadRequestException;
import com.kataacademy.schoolportal.common.personcontrollers.exception.BodyMessage;
import com.kataacademy.schoolportal.common.personcontrollers.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonHandlerException {

    @ExceptionHandler
    public ResponseEntity<BodyMessage> handlerException(
            PersonNotFoundException exception
    ) {
        BodyMessage data = new BodyMessage();
        data.setMessage(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BodyMessage> handlerException(
            BadRequestException exception
    ) {
        BodyMessage data = new BodyMessage();
        data.setMessage(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    //TODO добавить обработку прочих ошибок
}
