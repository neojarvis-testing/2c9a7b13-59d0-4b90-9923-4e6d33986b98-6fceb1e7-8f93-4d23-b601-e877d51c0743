package com.examly.springappuser.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springappuser.jwt.JwtService;
import com.examly.springappuser.model.LoginDTO;
import com.examly.springappuser.model.User;
import com.examly.springappuser.response.ResponseHandler;
import com.examly.springappuser.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class AuthController {
    
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {
        return ResponseHandler.generateResponse("Registration Successfully", HttpStatus.CREATED, this.userService.registerUser(user));
    }
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        final User user = this.userService.getUserByEmail(email);
        String password = loginDTO.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));
        if(authentication.isAuthenticated()) {
            String token = this.jwtService.generateToken(user);
            return ResponseHandler.generateResponse("Logged in successfully!", HttpStatus.OK, token);
        } else {
            return ResponseHandler.generateResponse("Login failed", HttpStatus.UNAUTHORIZED, null);
        }
    }
}
