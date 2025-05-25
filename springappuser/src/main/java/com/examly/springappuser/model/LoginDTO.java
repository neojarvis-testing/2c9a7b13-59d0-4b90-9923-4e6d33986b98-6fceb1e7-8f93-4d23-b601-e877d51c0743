package com.examly.springappuser.model;

public class LoginDTO {
    
    public LoginDTO() {}

    private String email;
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return this.email;}

    private String password;
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return this.password;}

    public LoginDTO(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Email: " + email + ", Password: " + password;
    }
}
