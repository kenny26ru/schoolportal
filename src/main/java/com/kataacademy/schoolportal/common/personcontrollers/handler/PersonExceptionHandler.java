package com.kataacademy.schoolportal.common.personcontrollers.handler;

import com.kataacademy.schoolportal.common.personcontrollers.exception.PersonForbiddenException;
import com.kataacademy.schoolportal.common.personcontrollers.exception.BodyMessage;
import com.kataacademy.schoolportal.common.personcontrollers.exception.PersonNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обрабатывает исключения, когда в БД не найдена запись
     */
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<BodyMessage> handlerException(
            PersonNotFoundException ex) {
        BodyMessage body = new BodyMessage(ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Обрабатывает исключение, когда получен запрос с нарушеным форматом JSON
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new BodyMessage("ERROR: Нарушен JSON формат", ex.getMessage()), status);
    }

    /**
     * Обрабатывает исключения, когда данные JSON не прошли валидацию
     * В pom.xml подключил spring-boot-starter-validation
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        BodyMessage body = new BodyMessage("ERROR: Ошибка в JSON данных", ex.getMessage(), errors);
        return new ResponseEntity<>(body, status);
    }

    /**
     * Обрабатывает исключения, когда в строке адреса задан неверный тип переменной
     * Например:
     *      Верное значение /api/users/2
     *      Неверное значение /api/users/abc
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        BodyMessage body = new BodyMessage();
        Class<?> aClass = ex.getRequiredType();
        body.setMessage(String.format("ERROR: Параметру '%s' задано неверное значение '%s'%s",
                ex.getName(), ex.getValue(),
                aClass==null?"":". Допустимый тип '" + aClass.getSimpleName() + "'"));
        body.setDebug(ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обрабатывает исключения, когда получен запрос для которого нет обработчиков
     * TODO перехватывается vaadin'ом, что делать?
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new BodyMessage("ERROR: Обработчик для запроса не существует", ex.getMessage()), status);
    }

    /**
     * Обрабатывает исключения, связанные с доступом к ресурсу.
     * TODO проверить!
     */
    public ResponseEntity<BodyMessage> handlerException(PersonForbiddenException ex) {
        BodyMessage message = new BodyMessage();
        message.setMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    /**
     * Обрабатывает прочие исключения, не обработанные выше
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        BodyMessage body = new BodyMessage("Внутренняя ошибка", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
