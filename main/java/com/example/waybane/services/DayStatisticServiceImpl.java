package com.example.waybane.services;


import com.example.waybane.models.Link;
import com.example.waybane.models.DayStatistic;
import com.example.waybane.repositories.DayStatisticRepository;
import com.example.waybane.repositories.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayStatisticServiceImpl implements DayStatisticService {

    private final LinkRepository linkRepository;
    private final DayStatisticRepository dayStatisticRepository;

    @Override
    public void addStatisticToLink(Link link) {
        Optional<DayStatistic> statistic = dayStatisticRepository.findByLinkAndDay(link, LocalDate.now());

        statistic.ifPresentOrElse(value -> {
            value.setRedirections(value.getRedirections() + 1);
            dayStatisticRepository.save(value);
        }, () -> {
            link.addDayStatistic(new DayStatistic());
            linkRepository.save(link);
        });
    }

    @Override
    public List<DayStatistic> findMostRedirected(Link link) {
        return dayStatisticRepository.findMostRedirected(link);
    }

    @Override
    public Optional<Long> getTotal(Link link) {
        return Optional.ofNullable(dayStatisticRepository.getTotal(link.getId()));
    }

    @Override
    public Optional<LocalDate> getLastRedirectionDay(Link link) {
        return Optional.ofNullable(dayStatisticRepository.getLastVisitDay(link));
    }
}
