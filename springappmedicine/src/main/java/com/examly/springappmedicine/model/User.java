package com.examly.springappmedicine.model;

public class User {
    /**
     * Default constructor
     */
    public User() {}

    private int userId;
    public void setUserId(int userId) {this.userId = userId;}
    public int getUserId() {return this.userId;}

    private String email;
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return this.email;}

    private String password;
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return this.password;}

    private String username;
    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return this.username;}

    private String mobileNumber;
    public void setMobileNumber(String mobileNumber) {this.mobileNumber = mobileNumber;}
    public String getMobileNumber() {return this.mobileNumber;}

    private String userRole;
    public void setUserRole(String userRole) {this.userRole = userRole;}
    public String getUserRole() {return this.userRole;}

    /**
     * Parameterized constructor
     * @param userId
     * @param email
     * @param password
     * @param username
     * @param mobileNumber
     * @param userRole
     */
    public User(int userId, String email, String password, String username, String mobileNumber, String userRole) {
        super();
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "ID: " + userId + ", Email: " + email + ", Password: " + password
        + ", username: " + username + ", Mobile number: " + mobileNumber + ", Role: " + userRole;
    }
}
