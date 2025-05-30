package com.examly.springappuser.service;

import com.examly.springappuser.exception.UserNotFoundException;
import com.examly.springappuser.model.User;

public interface UserService {
    User registerUser(User user);
    User getUserByEmail(String email) throws UserNotFoundException;
    User getUserById(int userId) throws UserNotFoundException;
}
