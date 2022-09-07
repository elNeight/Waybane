package com.example.waybane.controllers;

import com.example.waybane.models.RegistrationForm;
import com.example.waybane.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;


    @GetMapping
    public String registration(RegistrationForm form) {
        return "registration";
    }


    @PostMapping
    public String processRegistration(@Valid RegistrationForm form, Errors errors, Model model) {

        if (errors.hasErrors())
            return "registration";
        else if (!form.matchPasswords()) {
            model.addAttribute("error", "Passwords don't match");
            return "registration";
        } else if (userService.save(form.toUser()))
            return "redirect:/login";

        model.addAttribute("error", "User already exists");

        return "registration";
    }

}
