package com.example.waybane.controllers;

import com.example.waybane.models.Link;
import com.example.waybane.services.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    @Value("${page.size}")
    private int size;
    private final LinkService linkService;

    @GetMapping
    public String mainPage(
            Link link,
            Model model,
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").ascending());
        model.addAttribute("links", linkService.getLinks(pageable));
        return "main";
    }


    @PostMapping
    public String processUrl(
            @Valid Link link,
            Errors errors,
            Model model,
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").ascending());

        if (errors.hasErrors()) {
            model.addAttribute("links", linkService.getLinks(pageable));
            return "main";
        } else if (linkService.add(link))
            return "redirect:/main";

        model.addAttribute("error", "URL already shortened");
        model.addAttribute("links", linkService.getLinks(pageable));

        return "main";
    }

}
