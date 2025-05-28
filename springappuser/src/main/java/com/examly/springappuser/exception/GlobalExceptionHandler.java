package com.examly.springappuser.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.examly.springappuser.response.ResponseHandler;
import java.util.HashMap;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;

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
    private String getErrorMessage(String message) {
        int start = message.indexOf('\'');
        int end = message.indexOf('\'', start+1);
        if(start == -1 || end == -1) {
            return "constraint violation";
        }
        String value = message.substring(start+1, end);
        if(value.contains("@")) {
            return "Email already exists";
        } else if(value.matches("\\d+")) {
            return "mobile number already exists";
        } else {
            return "username already exists";
        }
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> constraintViolationExceptionHandler(DataIntegrityViolationException exception) {
        String message = exception.getRootCause() != null ? exception.getRootCause().getMessage() : exception.getMessage();
        String errorMessage = getErrorMessage(message);
        return ResponseHandler.generateResponse("Duplicate data is provided", HttpStatus.CONFLICT, errorMessage);
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> userNotFoundExceptionHandler(UserNotFoundException exception) {
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND, null);
    }
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> authenticationExceptionHandler(AuthenticationException exception) {
        return ResponseHandler.generateResponse("Invalid credentials", HttpStatus.UNAUTHORIZED, null);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> unhandledExceptionHandler(Exception exception) {
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED, null);
    }
}
