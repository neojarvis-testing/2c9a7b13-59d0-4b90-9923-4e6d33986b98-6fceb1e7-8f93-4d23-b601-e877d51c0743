package com.examly.springappuser.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
