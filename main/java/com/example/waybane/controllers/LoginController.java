package com.example.waybane.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Model model
    ) {

        if (Objects.nonNull(error))
            model.addAttribute("error", "Wrong credentials");
        if (Objects.nonNull(logout))
            model.addAttribute("logout", "You have been logged out");

        return "login";
    }


}
