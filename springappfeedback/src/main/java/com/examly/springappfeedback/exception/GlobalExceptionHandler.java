package com.examly.springappfeedback.exception;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.examly.springappfeedback.response.ResponseHandler;

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
    @ExceptionHandler(FeedbackNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> feedbackNotFoundExceptionHandler(FeedbackNotFoundException exception) {
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND, null);
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> accessDeniedExceptionHandler(AccessDeniedException exception) {
        return ResponseHandler.generateResponse("You do not have permission to access this resource", HttpStatus.FORBIDDEN, null);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> unhandledExceptionHandler(Exception exception) {
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}
