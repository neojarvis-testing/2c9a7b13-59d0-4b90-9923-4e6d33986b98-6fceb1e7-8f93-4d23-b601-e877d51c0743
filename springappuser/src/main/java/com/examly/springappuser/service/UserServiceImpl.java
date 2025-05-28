package com.examly.springappuser.service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.examly.springappuser.exception.UserNotFoundException;
import com.examly.springappuser.model.User;
import com.examly.springappuser.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public User registerUser(User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        return this.userRepo.save(user);
    }
    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        Optional<User> user = this.userRepo.findByEmail(email);
        if(user.isEmpty()) {
            throw new UserNotFoundException("Invalid email or password");
        }
        return user.get();
    }
}
