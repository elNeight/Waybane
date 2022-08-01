package com.example.waybane.services;

import com.example.waybane.models.Link;

import java.util.List;
import java.util.Optional;

public interface LinkService {

    boolean add(Link link);

    List<Link> getLinks();

    Optional<Link> findByToken(String token);

    Link update(Link link);

}
