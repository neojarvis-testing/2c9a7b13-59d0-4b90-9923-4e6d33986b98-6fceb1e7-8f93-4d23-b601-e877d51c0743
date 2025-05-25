package com.examly.springappuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springappuser.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByUsername(String username);
    User findByEmail(String email);
}
