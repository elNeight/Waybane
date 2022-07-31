package com.example.waybane.repositories;

import com.example.waybane.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByToken(String token);

}