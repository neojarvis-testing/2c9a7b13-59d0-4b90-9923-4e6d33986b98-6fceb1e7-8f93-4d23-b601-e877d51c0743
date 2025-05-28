package com.examly.springappmedicine.exception;

public class MedicalHistoryIdNotFoundException extends RuntimeException {
    public MedicalHistoryIdNotFoundException (String errormessage){
        super(errormessage);
    } 
}
