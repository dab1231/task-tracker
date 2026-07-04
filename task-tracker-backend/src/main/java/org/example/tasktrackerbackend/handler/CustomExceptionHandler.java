package org.example.tasktrackerbackend.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.tasktrackerbackend.dto.response.ErrorResponse;
import org.example.tasktrackerbackend.exception.TaskNotFoundException;
import org.example.tasktrackerbackend.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        var message = ex.getMessage();
        log.warn(message);

        return new ResponseEntity<>(
                new ErrorResponse(message),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> handleAuthExceptions(Exception ex) {
        var message = ex.getMessage();
        log.warn(message);

        return new ResponseEntity<>(
                new ErrorResponse("Email or password is not correct"),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        var fieldErrors = ex.getBindingResult().getFieldErrors();
        var message = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex) {

        var message = "Unexpected exception";
        log.error(message, ex);

        return new ResponseEntity<>(
                new ErrorResponse(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException(TaskNotFoundException ex) {

        var message = ex.getMessage();
        log.warn(message);

        return new ResponseEntity<>(
                new ErrorResponse(message),
                HttpStatus.NOT_FOUND
        );
    }
}
