package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.model.dto.RoleDto;
import com.brainstation.fantasyfootball.model.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author BS987
 * created date: 10/8/2022
 */
@Component
public class UserDetailsImpl implements UserDetails {

    List<GrantedAuthority> authorities = new ArrayList<>();
    private String username;
    private String password;
    private boolean isEnabled;

    public UserDetailsImpl(){}
    public UserDetailsImpl(UserDto user, List<RoleDto> roles){
        this.username = user.getUsername();
        this.password = user.getPassword();
        for(RoleDto role: roles){
            GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRole());
            this.authorities.add(simpleGrantedAuthority);
        }
        this.isEnabled = user.getEnabled();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}
