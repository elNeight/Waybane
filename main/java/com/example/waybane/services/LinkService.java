package com.example.waybane.services;

import com.example.waybane.models.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LinkService {

    boolean add(Link link);

    Page<Link> getLinks(Pageable pageable);

    Optional<Link> findByToken(String token);

    void deleteByToken(String token);

}
