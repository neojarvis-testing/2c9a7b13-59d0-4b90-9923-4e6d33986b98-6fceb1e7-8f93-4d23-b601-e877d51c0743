package com.examly.springappuser.service;

import com.examly.springappuser.exception.UserAlreadyExistsException;
import com.examly.springappuser.exception.UserNotFoundException;
import com.examly.springappuser.model.User;

public interface UserService {
    User registerUser(User user) throws UserAlreadyExistsException;
    public User getUserByEmail(String email) throws UserNotFoundException;
}
