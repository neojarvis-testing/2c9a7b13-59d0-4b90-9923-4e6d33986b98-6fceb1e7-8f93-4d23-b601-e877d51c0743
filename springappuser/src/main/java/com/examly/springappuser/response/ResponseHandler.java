package com.examly.springappuser.response;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    
    public static ResponseEntity<Object> generateResponse (String message, HttpStatus status, Object response) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("message", message);
        data.put("status", status);
        data.put("response", response);
        return new ResponseEntity<>(data, status);
    }
}
