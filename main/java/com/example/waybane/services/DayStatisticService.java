package com.example.waybane.services;

import com.example.waybane.models.DayStatistic;
import com.example.waybane.models.Link;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayStatisticService {

    void addStatisticToLink(Link link);

    List<DayStatistic> findMostRedirected(Link link);

    Optional<Long> getTotal(Link link);

    Optional<LocalDate> getLastRedirectionDay(Link link);

}
