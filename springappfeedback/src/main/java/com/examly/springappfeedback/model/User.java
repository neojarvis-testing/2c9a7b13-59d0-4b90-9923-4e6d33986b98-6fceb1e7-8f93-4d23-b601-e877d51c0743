package com.examly.springappfeedback.model;

public class User {
    
    public User() {super();}

    private int userId;
    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private String userRole;

    public User(int userId, String email, String password, String username, String mobileNumber, String userRole) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }

    public void setUserId(int userId) {this.userId = userId;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setUsername(String username) {this.username = username;}
    public void setMobileNumber(String mobileNumber) {this.mobileNumber = mobileNumber;}
    public void setUserRole(String userRole) {this.userRole = userRole;}

    public int getUserId() {return this.userId;}
    public String getEmail() {return this.email;}
    public String getPassword() {return this.password;}
    public String getUsername() {return this.username;}
    public String getMobileNumber() {return this.mobileNumber;}
    public String getUserRole() {return this.userRole;}

    @Override
    public String toString() {
        return "ID: " + userId + ", Email: " + email + ", Password: " + password + ", Username: " + username
                + ", Mobile number: " + mobileNumber + ", Role: " + userRole;
    }
}
