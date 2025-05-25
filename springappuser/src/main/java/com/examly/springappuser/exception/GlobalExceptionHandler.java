package com.examly.springappuser.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.examly.springappuser.response.ResponseHandler;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> formValidationExceptionHandler(MethodArgumentNotValidException exception) {
        HashMap<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(err -> {
            String field = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            errors.put(field, message);
        });
        return ResponseHandler.generateResponse("Invalid inputs", HttpStatus.BAD_REQUEST, errors);
    }
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> userAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.CONFLICT, null);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> usernameNotFoundExceptionHandler(UsernameNotFoundException exception) {
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> unhandledExceptionHandler(Exception exception) {
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}
