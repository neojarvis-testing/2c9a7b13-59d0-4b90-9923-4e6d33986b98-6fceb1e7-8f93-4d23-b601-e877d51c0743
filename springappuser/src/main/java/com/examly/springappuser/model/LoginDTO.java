package com.examly.springappuser.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDTO {

    public LoginDTO() {super();}

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotBlank(message = "Email is mandatory")
    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return this.email;}
    public String getPassword() {return this.password;}

    @Override
    public String toString() {
        return "Email: " + email + ", Password: " + password;
    }
}
