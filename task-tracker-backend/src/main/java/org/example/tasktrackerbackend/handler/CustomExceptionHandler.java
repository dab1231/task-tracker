package org.example.tasktrackerbackend.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.tasktrackerbackend.dto.response.ErrorResponse;
import org.example.tasktrackerbackend.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(Exception ex) {
        var message = ex.getMessage();
        log.warn(message);

        return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.CONFLICT);
    }
}
