package com.brainstation.fantasyfootball.configuration;

import com.brainstation.fantasyfootball.model.entity.User;
import com.brainstation.fantasyfootball.model.entity.UserRole;
import com.brainstation.fantasyfootball.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class AdminConfiguration implements CommandLineRunner {

    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("Fantasy");
        user.setLastName("Football");
        user.setUsername("fintech");
        user.setPassword(encoder.encode("bs23"));
        user.setEmail("shahinurbeg@gmail.com");
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole("ROLE_ADMIN");
        roles.add(role);
        user.setRoles(roles);
        user.setEnabled(true);
        Optional<User> admin = Optional.ofNullable(repository.findByEmail("shahinurbeg@gmail.com"));
        if (!admin.isPresent())
            repository.save(user);
    }

}
