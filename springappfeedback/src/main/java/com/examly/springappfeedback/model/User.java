package com.examly.springappfeedback.model;

import org.springframework.stereotype.Component;

@Component
public class User {

    public User() {super();}
    private int userId;
    public User(int userId) {
        this.userId = userId;
    }
    public void setUserId(int userId) {this.userId = userId;}
    public int getUserId() {return this.userId;}
    @Override
    public String toString() {
        return "ID: " + userId;
    }
}
