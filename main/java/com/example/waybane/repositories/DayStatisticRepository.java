package com.example.waybane.repositories;

import com.example.waybane.models.DayStatistic;
import com.example.waybane.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayStatisticRepository extends JpaRepository<DayStatistic, Long> {

    Optional<DayStatistic> findByLinkAndDay(Link link, LocalDate day);

    // отработало с подзапросом - вернуть нормальный объект(может вернуть несколько строк(если в два дня одинковое число переходов))
    @Query("select s from DayStatistic s where s.link = ?1 and s.redirections = " +
            "(select max(ss.redirections) from DayStatistic ss where ss.link = ?1)")
    List<DayStatistic> findMostRedirected(Link link);

    @Query(value = "select sum(redirections) from day_statistic where link_id = ?1 group by link_id", nativeQuery = true)
    Long getTotal(Long id);

    @Query("select max(s.day) from DayStatistic s where s.link = ?1")
    LocalDate getLastVisitDay(Link link);

}
