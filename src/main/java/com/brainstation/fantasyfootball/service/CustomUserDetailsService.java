package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.model.dto.RoleDto;
import com.brainstation.fantasyfootball.service.impl.UserDetailsImpl;
import com.brainstation.fantasyfootball.model.dto.UserDto;
import com.brainstation.fantasyfootball.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author BS987
 * created date: 10/1/2022
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userRepository.findUserByUsername(username);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("User Not Found!!");
        }
        Long id = user.getId();
        List<RoleDto> roles = userRepository.findRoleById(id);
        UserDetailsImpl userDetails = new UserDetailsImpl(user,roles);

        return userDetails;
    }
}
