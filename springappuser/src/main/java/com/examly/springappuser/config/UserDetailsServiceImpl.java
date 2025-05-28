package com.examly.springappuser.config;

import java.util.Optional;
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
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = this.userRepo.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid login credentials");
        }
        return new UserPrincipal(user.get());
    }
}
