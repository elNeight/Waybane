package com.example.waybane.controllers;

import com.example.waybane.models.Link;
import com.example.waybane.services.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final LinkService linkService;


    @GetMapping
    public String greetingPage() {
        return "greeting";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("links", linkService.getLinks());
        return "main";
    }

    @PostMapping("/main")
    public String processUrl(@Valid Link link, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("links", linkService.getLinks());
            return "main";
        } else if (linkService.add(link))
            return "redirect:/main";

        model.addAttribute("error", "URL already shortened");
        model.addAttribute("links", linkService.getLinks());

        return "main";
    }


    @ModelAttribute
    public Link link() {
        return new Link();
    }

}
