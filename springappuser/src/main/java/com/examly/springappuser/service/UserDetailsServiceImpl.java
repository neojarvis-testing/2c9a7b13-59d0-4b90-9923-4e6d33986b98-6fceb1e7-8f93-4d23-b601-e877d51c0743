package com.examly.springappuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examly.springappuser.model.User;
import com.examly.springappuser.model.UserPrincipal;
import com.examly.springappuser.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = this.userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid login credentials!");
        }
        return new UserPrincipal(user);
    }
    
}
