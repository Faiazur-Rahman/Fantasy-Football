package com.brainstation.fantasyfootball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String home() {
        return "user/home";
    }
}
