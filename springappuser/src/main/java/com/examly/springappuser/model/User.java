package com.examly.springappuser.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    public User() {super();}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}

    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please enter a valid email")
    private String email;
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String password;
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    @Column(unique = true)
    @NotBlank(message = "username is mandatory")
    private String username;
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    @Column(unique = true)
    @NotBlank(message = "mobile number is mandatory")
    private String mobileNumber;
    public String getMobileNumber() {return mobileNumber;}
    public void setMobileNumber(String mobileNumber) {this.mobileNumber = mobileNumber;}

    @NotBlank(message = "role is mandatory")
    private String userRole;
    public String getUserRole() {return userRole;}
    public void setUserRole(String userRole) {this.userRole = userRole;}

    public User(int userId, String email, String password, String username, String mobileNumber, String userRole) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }
    
    @Override
    public String toString() {
        return "userId: " + userId + ", email: " + email + ", password: " + password + ", username: " + username
                + ", mobileNumber: " + mobileNumber + ", userRole: " + userRole;
    }
}
