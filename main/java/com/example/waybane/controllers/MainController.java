package com.example.waybane.controllers;

import com.example.waybane.models.Link;
import com.example.waybane.services.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final LinkService linkService;


    @GetMapping
    public String greetingPage() {
        return "greeting";
    }


    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }


    @PostMapping("/main")
    public String processUrl(@Valid Link link, Errors errors) {

        if (errors.hasErrors())
            return "main";

        linkService.add(link);
        return "redirect:/main";
    }


    @ModelAttribute
    public Link link() {
        return new Link();
    }

}
