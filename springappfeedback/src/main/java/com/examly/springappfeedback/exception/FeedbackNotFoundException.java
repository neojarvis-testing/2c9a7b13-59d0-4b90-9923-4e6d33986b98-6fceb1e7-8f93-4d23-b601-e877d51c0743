package com.examly.springappfeedback.exception;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
