package com.example.waybane.controllers;

import com.example.waybane.models.DayStatistic;
import com.example.waybane.models.Link;
import com.example.waybane.services.DayStatisticService;
import com.example.waybane.services.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class StatisticsController {

    private final LinkService linkService;
    private final DayStatisticService statisticService;

    @GetMapping("/links/{token}")
    public String statistics(@PathVariable String token, Model model) {
        Optional<Link> link = linkService.findByToken(token);

        link.ifPresent(value -> {
            model.addAttribute("link", value);

            statisticService.getTotal(value)
                    .ifPresent(s -> model.addAttribute("total", s));
            statisticService.getLastRedirectionDay(value)
                    .ifPresent(s ->model.addAttribute("lastDay", s));



            List<DayStatistic> statistics = statisticService.findMostRedirected(value);
            if (!statistics.isEmpty())
                model.addAttribute("mostRedirected", statistics);

        });

        return "statistics";
    }




}
