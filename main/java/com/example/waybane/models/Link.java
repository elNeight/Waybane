package com.example.waybane.models;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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

    private Long transitions;
    private LocalDateTime creationTime;

    public Link() {
        transitions = 0L;
    }

    @PrePersist
    private void prePersist() {
        creationTime = LocalDateTime.now();
    }

}
