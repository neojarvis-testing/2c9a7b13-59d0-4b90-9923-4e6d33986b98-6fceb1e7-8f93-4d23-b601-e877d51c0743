package com.examly.springappmedicine.exception;

public class PatientIdNotFoundException extends RuntimeException {
    public PatientIdNotFoundException(String errorMessage){
        super(errorMessage);
    }
    
}
