package com.example.waybane.repositories;

import com.example.waybane.models.Link;
import com.example.waybane.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByToken(String token);

    Optional<Link> findByUrl(String url);

    Optional<Link> findByUserAndUrl(User user, String url);

    List<Link> findByUser(User user);

}