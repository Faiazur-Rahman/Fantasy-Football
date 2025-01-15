package com.brainstation.fantasyfootball.mapper;

import com.brainstation.fantasyfootball.model.entity.User;
import com.brainstation.fantasyfootball.model.entity.UserRole;
import com.brainstation.fantasyfootball.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shahinur Beg
 * created date: 10/20/2022
 */
@Component
public class UserMapper {
    @Autowired
    private BCryptPasswordEncoder encoder;
    public User toUser(SignupRequest signupRequest){
        User user = new User();
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setUsername(signupRequest.getUsername());
        user.setEnabled(false);
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setRole("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        return user;

    }
}
