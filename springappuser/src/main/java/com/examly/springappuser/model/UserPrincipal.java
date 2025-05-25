package com.examly.springappuser.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private final User user;
    private final boolean flag = true;
    @Autowired
    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getUserRole()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return flag;
    }

    @Override
    public boolean isAccountNonLocked() {
        return flag;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return flag;
    }

    @Override
    public boolean isEnabled() {
        return flag;
    }
    
}
