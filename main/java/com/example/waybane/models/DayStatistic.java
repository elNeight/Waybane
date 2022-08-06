package com.example.waybane.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class DayStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day;
    private Long redirections;

    @ManyToOne(fetch = FetchType.LAZY)
    private Link link;

    @PrePersist
    private void prePersist() {
        day = LocalDate.now();
        redirections = 1L;
    }

}
