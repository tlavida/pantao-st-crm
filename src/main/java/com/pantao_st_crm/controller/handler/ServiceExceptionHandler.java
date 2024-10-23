package com.pantao_st_crm.controller.handler;

import com.pantao_st_crm.exception.CustomEntityNotFoundException;
import com.pantao_st_crm.exception.EmployeeAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ControllerAdvice позволяет создавать глобальные хендлеры,
 * которые обрабатывают исключения во всех контроллерах приложения.
 * @Exception Handlers предоставляют способ централизованно обрабатывать
 * исключения и предоставлять пользователям корректные ответы.
 */

@ControllerAdvice
public class ServiceExceptionHandler {
    @ExceptionHandler(CustomEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleCustomEntityNotFoundException(CustomEntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // 409 Conflict
            public ResponseEntity<String> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
}
