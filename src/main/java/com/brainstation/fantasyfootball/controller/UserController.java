package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.model.entity.User;
import com.brainstation.fantasyfootball.request.SignupRequest;
import com.brainstation.fantasyfootball.request.UserRequest;
import com.brainstation.fantasyfootball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/userHomePage")
    public String userHomePage(){
        return "user/home";
    }
    @GetMapping("/adminHomePage")
    public String adminHomePage(){
         return "admin/adminHome";
    }
    @GetMapping("/signupForm")
    public String signupForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "common/signup";
    }
    @PostMapping("/signup")
    public String userSignUp(@ModelAttribute("user") SignupRequest signupRequest, RedirectAttributes redirectAttributes){
        ServiceResponse<?> response = userService.signup(signupRequest);
        if(response.isHasError()){
            redirectAttributes.addFlashAttribute("message",response.getErrorMsg());
            return "redirect:/user/signupForm";
        } else {
            redirectAttributes.addFlashAttribute("message",response.getSuccessMsg());
            return "redirect:/user/otpForm";
        }
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "common/login";
    }
    @GetMapping("/error_login")
    public String errorLogin(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Username or Password not Match!!");
        return "redirect:/user/loginForm";
    }
    @GetMapping("/homepage")
    public String homePage(HttpServletRequest request, RedirectAttributes redirectAttributes){
        Principal principal = request.getUserPrincipal();
        String name = principal.getName();
        ServiceResponse<?> response = userService.getRole(name);
        if(response.getSuccessMsg().equals("ADMIN")){
            redirectAttributes.addFlashAttribute("message","Welcome as Admin");
            return "redirect:/admin/admin_page";
        }
        else{
            redirectAttributes.addFlashAttribute("message","Welcome "+ name +"!");
            return "redirect:/";
        }
    }

    @GetMapping("/otpForm")
    public String otpForm(){
        return "common/OTP";
    }
    @PostMapping("/otp")
    public String submitOtp(@RequestParam("otp") Integer otp, RedirectAttributes redirectAttributes){
        ServiceResponse<?> response = userService.submitOtp(otp);
        if(response.isHasError()){
            redirectAttributes.addFlashAttribute("message",response.getErrorMsg());
            return "redirect:/user/otpForm";
        } else {
            redirectAttributes.addFlashAttribute("message",response.getSuccessMsg());
            return "redirect:/user/loginForm";
        }
    }

    @GetMapping("/forgetPassword")
    public  String forgetPassword() {
        return "common/forget_password";

    }

    @PostMapping("/forgetPassword")
    public String sendForgetOtp( @RequestParam("email") String email){
        userService.sendEmail(email);
        return "redirect:/user/sendForgetOtp";
    }

    @GetMapping("/sendForgetOtp")
    public String sendForgetPasswordOtp(){
       return "common/forget_password_otp";

    }

    @PostMapping("/sendForgetOtp")
    public String sendForgetPasswordOtp(@RequestParam Map<String,String> requestParams){

        String password = requestParams.get("password");
        String otp = requestParams.get("otp");
        System.out.println(password);
        userService.sendForgetPasswordOtp(password, Integer.valueOf(otp));
        return "redirect:/user/loginForm";

    }
    @PostMapping("/searchUsername")
    public ResponseEntity<?> getUsername(@RequestBody UserRequest userRequest){
         return userService.getUsername(userRequest.getUserName());
    }

}
