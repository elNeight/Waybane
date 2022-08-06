package com.example.waybane.models;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @NotBlank(message = "URL must not be blank")
    @URL(message = "Input is not a valid URL")
    private String url;
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "link", cascade = CascadeType.ALL)
    private List<DayStatistic> dayStatistics;

    public void addDayStatistic(DayStatistic dayStatistic) {
        if (Objects.isNull(dayStatistics))
            dayStatistics = new ArrayList<>();

        dayStatistics.add(dayStatistic);
        dayStatistic.setLink(this);
    }

}
