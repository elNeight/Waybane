package com.example.waybane.controllers;

import com.example.waybane.models.Link;
import com.example.waybane.services.LinkService;
import com.example.waybane.services.DayStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RedirectController {

    private final LinkService linkService;
    private final DayStatisticService redirectionService;

    @GetMapping("/{token}")
    public String redirect(@PathVariable String token) {
        System.out.println("REDIRECT : " + token);
        Optional<Link> link = linkService.findByToken(token);

        return link.map(value -> {
            redirectionService.addStatisticToLink(value);
            return "redirect:" + value.getUrl();
        }).orElse("not_found");
    }

}
