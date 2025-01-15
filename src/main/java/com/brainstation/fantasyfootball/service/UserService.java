package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.model.dto.UserDto;
import com.brainstation.fantasyfootball.model.entity.Country;
import com.brainstation.fantasyfootball.model.entity.User;
import com.brainstation.fantasyfootball.request.SignupRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    ServiceResponse<?> signup(SignupRequest user);
    ServiceResponse<?> getRole(String username);
    ResponseEntity<?> deleteById(Long id);
//    List<User> showAllUser();
    ServiceResponse<?> submitOtp(Integer otp);
    ResponseEntity<?> sendEmail(String email);

    ResponseEntity<?> sendForgetPasswordOtp(String password, Integer otp);

    Page<User> getUsers(int page, int size);

    ResponseEntity<?> getUsername(String username);

    UserDto getUserByUsername(String username);
    User getUser(String username);
    void addUser(User user);
}
