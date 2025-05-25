package com.examly.springappuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springappuser.exception.UserAlreadyExistsException;
import com.examly.springappuser.model.User;
import com.examly.springappuser.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        String email = user.getEmail();
        if(this.userRepo.existsByEmail(email)) {
            throw new UserAlreadyExistsException("User already exists with that email ID");
        }
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        return this.userRepo.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

}
