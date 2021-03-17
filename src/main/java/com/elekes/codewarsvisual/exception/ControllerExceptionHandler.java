package com.elekes.codewarsvisual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    protected ResponseEntity<Object> userExists(UserExistsException e, WebRequest request) {
        return new ResponseEntity<Object>(new ErrorMessage(e), HttpStatus.CONFLICT);
    }

}
