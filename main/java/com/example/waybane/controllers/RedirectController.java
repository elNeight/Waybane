package com.example.waybane.controllers;

import com.example.waybane.models.Link;
import com.example.waybane.services.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RedirectController {

    private final LinkService linkService;

    @GetMapping("/{token}")
    public String redirect(@PathVariable String token) {

        System.out.println("REDIRECT : " + token);

        Optional<Link> link = linkService.findByToken(token);
        return link
                .map(value -> {
                    value.setTransitions(value.getTransitions() + 1);
                    linkService.update(value);
                    return "redirect:" + value.getUrl();
                })
                .orElse("not_found");
    }

}
