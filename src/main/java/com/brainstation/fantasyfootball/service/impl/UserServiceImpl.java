package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.mapper.UserMapper;
import com.brainstation.fantasyfootball.model.dto.RoleDto;
import com.brainstation.fantasyfootball.model.dto.UserDto;
import com.brainstation.fantasyfootball.model.entity.Country;
import com.brainstation.fantasyfootball.model.entity.OTP;
import com.brainstation.fantasyfootball.model.entity.User;
import com.brainstation.fantasyfootball.repository.OtpRepository;
import com.brainstation.fantasyfootball.repository.UserRepository;
import com.brainstation.fantasyfootball.request.SignupRequest;
import com.brainstation.fantasyfootball.service.MailService;
import com.brainstation.fantasyfootball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private ServiceResponse<?> serviceResponse;

    @Autowired
    private BCryptPasswordEncoder encoder;



    @Override
    public ServiceResponse<?> signup(SignupRequest signupRequest) {
        List<User> users= userRepository.findAll();

        for(User user : users){
            boolean active = user.isEnabled();
            if(active==true && user.getEmail().equals(signupRequest.getEmail())){
                serviceResponse.setHasError(true);
                serviceResponse.setErrorMsg("User Email Already Exist!");
                return serviceResponse;
            }
            if(user.getUsername().equals(signupRequest.getUsername())){
                serviceResponse.setHasError(true);
                serviceResponse.setErrorMsg("Username Already Exist!");
                return serviceResponse;
            }
            if(active==false && user.getEmail().equals(signupRequest.getEmail())){
                userRepository.deleteById(user.getId());
            }
        }

        Integer otp = mailService.sendMail(signupRequest.getEmail());
        User user = userMapper.toUser(signupRequest);
        OTP newOtp = new OTP(otp, user.getEmail());
        userRepository.save(user);
        otpRepository.save(newOtp);
        serviceResponse.setSuccessMsg("Email Sent!");
        return serviceResponse;
    }
    @Override
    public ServiceResponse<?> submitOtp(Integer otp){
        List<OTP> allOtp = otpRepository.findAll();
        for(OTP it: allOtp){
            if(it.getOtp().equals(otp)){
                String email = it.getUserMail();
                otpRepository.deleteById(it.getId());
                User user = userRepository.findByEmail(email);
                userRepository.deleteById(user.getId());
                user.setEnabled(true);
                userRepository.save(user);
                serviceResponse.setSuccessMsg("OPT is verified. Please login!");
                return serviceResponse;
            }
        }
        serviceResponse.setHasError(true);
        serviceResponse.setErrorMsg("Not Match the OTP!");
        return serviceResponse;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok("Successfully deleted ");
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseEntity.ok("User not found");
        }
    }
//    public List<User> showAllUser(){
//           return userRepository.findAll();
//    }
    @Override
    public ServiceResponse<?> getRole(String username) {
        UserDto user = userRepository.findUserByUsername(username);
        Long id = user.getId();
        List<RoleDto> roles = userRepository.findRoleById(id);
        serviceResponse.setSuccessMsg("USER");
        for (var role: roles){
            if(role.getRole().equals("ROLE_ADMIN")){
                serviceResponse.setSuccessMsg("ADMIN");
            }
        }
        return serviceResponse;
    }


    @Override
    public ResponseEntity<?> sendEmail(String email){
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        Integer otp = mailService.sendMail(email);
        OTP newOtp = new OTP(otp,user.getEmail());
        otpRepository.save(newOtp);
        return ResponseEntity.ok("Successfully OTP Generated");
    }

    public  ResponseEntity<?> sendForgetPasswordOtp(String password, Integer otp){

        List<OTP> allOtps = otpRepository.findAll();

        for(OTP it: allOtps){
            if(it.getOtp().equals(otp)){
                String email = it.getUserMail();
                otpRepository.deleteById(it.getId());
                User user = userRepository.findByEmail(email);
                user.setPassword(encoder.encode(password));
                userRepository.deleteById(user.getId());
                userRepository.save(user);

                return ResponseEntity.ok("Successful!!");
            }
        }
        return ResponseEntity.ok("Not match the otp code");
    }

    @Override
    public ResponseEntity<?> getUsername(String username) {
        Optional<UserDto> userByUsername = Optional.ofNullable(userRepository.findUserByUsername(username));
        if(userByUsername.isPresent()){
            return ResponseEntity.ok(HttpStatus.OK);
        }else {
            return new  ResponseEntity("NOT FOUND",HttpStatus.NOT_FOUND);
        }
    }

    public UserDto getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }



    @Override
    public Page<User> getUsers(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        return users;
    }


}
